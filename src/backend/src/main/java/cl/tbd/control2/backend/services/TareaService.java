package cl.tbd.control2.backend.services;

import cl.tbd.control2.backend.entities.TareaEntity;
import cl.tbd.control2.backend.repositories.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Service
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
