package cl.tbd.control2.backend.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import cl.tbd.control2.backend.entities.TareaEntity;

@Repository
public class TareaRepositoryImp implements TareaRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public List<TareaEntity> getAllTareas() {
        String sql = "SELECT * FROM tarea";
        List<TareaEntity> listaTareas;

        try (Connection conn =  sql2o.open()) {
            listaTareas = conn.createQuery(sql).executeAndFetch(TareaEntity.class);
            
            return listaTareas;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener todas las tareas", e);
        }
    }

    @Override
    public List<TareaEntity> getAllTareasByUserId(Long userId) {
        String sql = "SELECT * FROM tarea WHERE id_usuario_tarea = :userId";
        List<TareaEntity> listaTareas;

        try (Connection conn = sql2o.open()) {
            listaTareas = conn.createQuery(sql)
                    .addParameter("userId", userId)
                    .executeAndFetch(TareaEntity.class);

            return listaTareas;
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error al obtener las tareas del usuario", e);
        }
    }

    @Override
    public TareaEntity getTareaById(Long id) {
        String sql = "SELECT * FROM tarea WHERE id_tarea = :id";
        try (Connection conn = sql2o.open()) {
            return conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(TareaEntity.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener la tarea por ID", e);
        }
    }

    @Override
    public void createTarea(TareaEntity tarea) {
        String sql = "INSERT INTO tarea (nombre_tarea, desc_tarea, vence_tarea, estado_tarea, id_usuario_tarea) VALUES (:nombre_tarea, :desc_tarea, :vence_tarea, :estado_tarea, :id_usuario_tarea)";

        try (Connection conn = sql2o.open()) {
            Integer idInteger = (Integer) conn.createQuery(sql, true)
                    .addParameter("nombre_tarea", tarea.getNombre_tarea())
                    .addParameter("desc_tarea", tarea.getDesc_tarea())
                    .addParameter("vence_tarea", tarea.getVence_tarea())
                    .addParameter("estado_tarea", tarea.getEstado_tarea())
                    .addParameter("id_usuario_tarea", tarea.getId_usuario_tarea())
                    .executeUpdate()
                    .getKey();

            Long id = idInteger.longValue();
            tarea.setId_tarea(id);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear la tarea", e);
        }
    }

    @Override
    public void updateTarea(TareaEntity tarea) {
        String sql = "UPDATE tarea SET nombre_tarea = :nombre_tarea, desc_tarea = :desc_tarea, vence_tarea = :vence_tarea, estado_tarea = :estado_tarea, id_usuario_tarea = :id_usuario_tarea WHERE id_tarea = :id_tarea";

        try (Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                    .addParameter("nombre_tarea", tarea.getNombre_tarea())
                    .addParameter("desc_tarea", tarea.getDesc_tarea())
                    .addParameter("vence_tarea", tarea.getVence_tarea())
                    .addParameter("estado_tarea", tarea.getEstado_tarea())
                    .addParameter("id_usuario_tarea", tarea.getId_usuario_tarea())
                    .addParameter("id_tarea", tarea.getId_tarea())
                    .executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar la tarea", e);
        }
    }
    @Override
    public void deleteTarea(Long id) {
        String sql = "DELETE FROM tarea WHERE id_tarea = :id";

        try (Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar la tarea", e);
        }
    }

    @Override
    public List<TareaEntity> getAllTareasUserbyEstado(Long userId, String estado) {
        String sql = "SELECT * FROM tarea WHERE id_usuario_tarea = :userId AND estado_tarea= :estado";
        List<TareaEntity> listaTareas;

        try (Connection conn = sql2o.open()) {
            return conn.createQuery(sql)
                    .addParameter("userId", userId)
                    .addParameter("estado", estado)
                    .executeAndFetch(TareaEntity.class);

        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error al obtener las tareas del usuario", e);
        }
    }
}
