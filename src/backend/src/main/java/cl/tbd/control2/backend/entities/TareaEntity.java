package cl.tbd.control2.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "tarea")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class TareaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idTarea;

    @Column(name = "nombreTarea")
    private String nombreTarea;

    @Column(name = "descTarea")
    private String descTarea;

    @Column(name = "venceTarea")
    private Date venceTarea;

    @Column(name = "estadoTarea")
    private String estadoTarea;

    @Column(name = "idUsuario")
    private Long idUsuario;
}
