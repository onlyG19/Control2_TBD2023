package cl.tbd.control2.backend.repositories;

import cl.tbd.control2.backend.entities.TareaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends CrudRepository<TareaEntity, Long>{
}
