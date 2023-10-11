package cl.tbd.control2.backend.services;

import java.util.List;

import cl.tbd.control2.backend.entities.*;
import cl.tbd.control2.backend.security.TokenUtils;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

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
            return ResponseEntity.ok(response);
        }
        else{
            response.setError(true);
            response.setMessage("El usuario ya existe!");
            response.setUsuario(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }


    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioLoginResponse> loginUsuario(@RequestBody LoginRequest loginRequest){
        // Buscar el usuario por nombre de usuario en la base de datos
        UsuarioEntity usuario = usuarioRepository.findOneByUsername(loginRequest.getNombre());
        UsuarioLoginResponse response = new UsuarioLoginResponse();

        if(usuario != null &&
                new BCryptPasswordEncoder().matches(loginRequest.getPassword(), usuario.getContrasenia_usuario())) {
            // El usuario existe y la contraseña coincide

            String token = TokenUtils.createToken(usuario.getNombre_usuario());

            // Configurar la respuesta con el token y otros datos necesarios
            response.setError(false);
            response.setMessage("Inicio de sesión exitoso");
            response.setToken(token);
            response.setUsuario(usuario);
            return ResponseEntity.ok(response);

        } else {
            // El usuario no existe o la contraseña es incorrecta
            response.setError(true);
            response.setMessage("Credenciales de inicio de sesión no válidas");
            response.setToken(null);
            response.setUsuario(null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

}
