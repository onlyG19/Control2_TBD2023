package cl.tbd.control2.backend.services;

import cl.tbd.control2.backend.entities.TareaEntity;
import cl.tbd.control2.backend.repositories.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Service
@CrossOrigin("http://localhost:3030")
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @GetMapping("/tareas")
    public List<TareaEntity> obtenerTodasLasTareas() {
        return tareaRepository.getAllTareas();
    }

    @GetMapping("/tarea/{id}")
    public TareaEntity obtenerTareaPorId(@PathVariable Long id) {
        return tareaRepository.getTareaById(id);
    }

    @GetMapping("/tareas/{userId}")
    public List<TareaEntity> obtenerTodasLasTareasUsuario(@PathVariable Long userId) {
        return tareaRepository.getAllTareasByUserId(userId);
    }

    @PostMapping("/tarea")
    public void crearTarea(@RequestBody TareaEntity tarea) {
        tareaRepository.createTarea(tarea);
    }

    @PutMapping("/tarea/{id}")
    public void actualizarTarea(@PathVariable Long id, @RequestBody TareaEntity tarea) {
        tarea.setId_tarea(id);
        tareaRepository.updateTarea(tarea);
    }

    @DeleteMapping("/tarea/{id}")
    public void eliminarTareaPorId(@PathVariable Long id) {
        tareaRepository.deleteTarea(id);
    }
}
