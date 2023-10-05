package cl.tbd.control2.backend.services;

import cl.tbd.control2.backend.entities.TareaEntity;
import cl.tbd.control2.backend.repositories.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public TareaEntity crear(TareaEntity tareaEntity){
        return tareaRepository.save(tareaEntity);
    }

    public Iterable<TareaEntity> obtenerTodos() {
        return tareaRepository.findAll();
    }

    public Optional<TareaEntity> obtenerPorID(Long id){
        return tareaRepository.findById(id);
    }

    public void eliminarPorID(Long id){
        tareaRepository.deleteById(id);
    }
}
