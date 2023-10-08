package cl.tbd.control2.backend.services;

import java.util.List;

import cl.tbd.control2.backend.entities.Registration;
import cl.tbd.control2.backend.entities.UsuarioRegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import cl.tbd.control2.backend.entities.UsuarioEntity;
import cl.tbd.control2.backend.repositories.UsuarioRepository;

@RestController
@Service
@RequestMapping("/usuario")
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/usuarios")
    public List<UsuarioEntity> obtenerTodosLosUsuarios() {
        return usuarioRepository.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioEntity obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioRepository.getUsuarioById(id);
    }

    @PostMapping("")
    public void crearUsuario(@RequestBody UsuarioEntity usuario) {
        usuarioRepository.createUsuario(usuario);
    }

    @PutMapping("/{id}")
    public void actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioEntity usuario) {
        usuario.setId_usuario(id);
        usuarioRepository.updateUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuarioPorId(@PathVariable Long id) {
        usuarioRepository.deleteUsuario(id);
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioRegisterResponse> registerUsuario(@RequestBody Registration registration){
        UsuarioEntity usuario = usuarioRepository.findOneByUsername(registration.getNombre());
        UsuarioRegisterResponse response = new UsuarioRegisterResponse();
        if(usuario == null){
            // Se crea usuario
            usuario = new UsuarioEntity();
            usuario.setNombre_usuario(registration.getNombre());
            usuario.setContrasenia_usuario(new BCryptPasswordEncoder().encode(registration.getPassword()));
            usuarioRepository.createUsuario(usuario);
            // Response
            response.setError(false);
            response.setMessage("El usuario se creo correctamente");
            response.setUsuario(usuario);
        }
        else{
            response.setError(true);
            response.setMessage("El usuario ya existe!");
            response.setUsuario(null);
        }
        return ResponseEntity.ok(response);

    }

}
