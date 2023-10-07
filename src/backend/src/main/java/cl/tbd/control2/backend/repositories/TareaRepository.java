package cl.tbd.control2.backend.repositories;

import java.util.List;

import cl.tbd.control2.backend.entities.TareaEntity;

public interface TareaRepository {
    List<TareaEntity> getAllTareas();
    TareaEntity getTareaById(Long id);
    void createTarea(TareaEntity tarea);
    void updateTarea(TareaEntity tarea);
    void deleteTarea(Long id);
}
