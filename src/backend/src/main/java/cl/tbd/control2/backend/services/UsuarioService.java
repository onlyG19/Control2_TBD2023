package cl.tbd.control2.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.tbd.control2.backend.entities.UsuarioEntity;
import cl.tbd.control2.backend.repositories.UsuarioRepository;

@RestController
@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public List<UsuarioEntity> obtenerTodosLosUsuarios() {
        return usuarioRepository.getAllUsuarios();
    }

    @GetMapping("/usuario/{id}")
    public UsuarioEntity obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioRepository.getUsuarioById(id);
    }

    @PostMapping("/usuario")
    public void crearUsuario(@RequestBody UsuarioEntity usuario) {
        usuarioRepository.createUsuario(usuario);
    }

    @PutMapping("/usuario/{id}")
    public void actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioEntity usuario) {
        usuario.setId_usuario(id);
        usuarioRepository.updateUsuario(usuario);
    }

    @DeleteMapping("/usuario/{id}")
    public void eliminarUsuarioPorId(@PathVariable Long id) {
        usuarioRepository.deleteUsuario(id);
    }
}
