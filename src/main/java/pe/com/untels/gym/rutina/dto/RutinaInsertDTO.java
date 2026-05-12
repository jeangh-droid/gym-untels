package pe.com.untels.gym.rutina.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class RutinaInsertDTO {
    private String nombre;
    private String descripcion;
    private LocalDate fechaCreacion;


}
