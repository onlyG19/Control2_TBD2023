package cl.tbd.control2.backend.repositories;

import java.util.List;

import cl.tbd.control2.backend.entities.UsuarioEntity;

public interface UsuarioRepository {
    List<UsuarioEntity> getAllUsuarios();
    UsuarioEntity getUsuarioById(Long id);
    void createUsuario(UsuarioEntity usuario);
    void updateUsuario(UsuarioEntity usuario);
    void deleteUsuario(Long id);
    UsuarioEntity findOneByUsername(String username);
}
