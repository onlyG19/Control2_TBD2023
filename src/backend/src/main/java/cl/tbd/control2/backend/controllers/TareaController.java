package cl.tbd.control2.backend.controllers;

import cl.tbd.control2.backend.entities.TareaEntity;
import cl.tbd.control2.backend.services.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @PostMapping("/tarea")
    public TareaEntity crear(@RequestBody TareaEntity tarea){
        return tareaService.crear(tarea);
    }

    @GetMapping("/tareas")
    public Iterable<TareaEntity> todos(){
        return tareaService.obtenerTodos();
    }

    @GetMapping("/tarea/{id}")
    public Optional<TareaEntity> obtenerTarea(@PathVariable Long id){
        return tareaService.obtenerPorID(id);
    }

    @DeleteMapping("/tarea/{id}")
    public void eliminarTarea(@PathVariable Long id){
        tareaService.eliminarPorID(id);
    }
}
