package cl.tbd.control2.backend.entities;

import lombok.Data;

@Data
public class UsuarioRegisterResponse {
    private Boolean error;
    private String message;
    private UsuarioEntity usuario;
}
