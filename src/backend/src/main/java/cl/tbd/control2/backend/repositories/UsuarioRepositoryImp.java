package cl.tbd.control2.backend.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import cl.tbd.control2.backend.entities.UsuarioEntity;

@Repository
public class UsuarioRepositoryImp implements UsuarioRepository {
    
    @Autowired
    private Sql2o sql2o;

    @Override
    public List<UsuarioEntity> getAllUsuarios() {
        String sql = "SELECT * FROM usuario";
        List<UsuarioEntity> listaUsuarios;

        try (Connection conn =  sql2o.open()) {
            listaUsuarios = conn.createQuery(sql).executeAndFetch(UsuarioEntity.class);
            
            return listaUsuarios;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener todos los usuarios", e);
        }
    }

    @Override
    public UsuarioEntity getUsuarioById(Long id) {
        String sql = "SELECT * FROM usuario WHERE id_usuario = :id";
        try (Connection conn = sql2o.open()) {
            return conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(UsuarioEntity.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener el usuario por ID", e);
        }
    }

    @Override
    public void createUsuario(UsuarioEntity usuario) {
        String sql = "INSERT INTO usuario (nombre_usuario, contrasenia_usuario) VALUES (:nombre_usuario, :contrasenia_usuario)";

        try (Connection conn = sql2o.open()) {
            Integer idInteger = (Integer) conn.createQuery(sql, true)
                    .addParameter("nombre_usuario", usuario.getNombre_usuario())
                    .addParameter("contrasenia_usuario", usuario.getContrasenia_usuario())
                    .executeUpdate()
                    .getKey();

            Long id = idInteger.longValue();
            usuario.setId_usuario(id);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear la usuario", e);
        }
    }

    @Override
    public void updateUsuario(UsuarioEntity usuario) {
        String sql = "UPDATE usuario SET nombre_usuario = :nombre_usuario, contrasenia_usuario = :contrasenia_usuario WHERE id_usuario = :id_usuario";

        try (Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                    .addParameter("nombre_usuario", usuario.getNombre_usuario())
                    .addParameter("contrasenia_usuario", usuario.getContrasenia_usuario())
                    .addParameter("id_usuario", usuario.getId_usuario())
                    .executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar la usuario", e);
        }
    }

    @Override
    public void deleteUsuario(Long id) {
        String sql = "DELETE FROM usuario WHERE id_usuario = :id";

        try (Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar la usuario", e);
        }
    }


    @Override
    public UsuarioEntity findOneByUsername(String username) {
        String sql = "SELECT * FROM usuario WHERE nombre_usuario = :username";
        Connection conn = sql2o.open();
        return conn.createQuery(sql)
                .addParameter("username", username)
                .executeAndFetchFirst(UsuarioEntity.class);
    }
}
