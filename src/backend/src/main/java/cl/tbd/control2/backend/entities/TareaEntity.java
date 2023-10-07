package cl.tbd.control2.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TareaEntity {
    private Long id_tarea;
    private String nombre_tarea;
    private String desc_tarea;
    private Date vence_tarea;
    private String estado_tarea;
    private Long id_usuario_tarea;
}
