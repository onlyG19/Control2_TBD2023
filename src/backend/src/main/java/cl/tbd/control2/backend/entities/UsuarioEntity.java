package cl.tbd.control2.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {
    private Long id_usuario;
    private String nombre_usuario;
    private String contrasenia_usuario;
}
