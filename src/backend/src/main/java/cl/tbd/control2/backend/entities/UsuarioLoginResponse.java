package cl.tbd.control2.backend.entities;

import lombok.Data;

@Data
public class UsuarioLoginResponse {
    private boolean error;
    private String message;
    private String token; // Token JWT generado para el usuario
    private UsuarioEntity usuario;
}
