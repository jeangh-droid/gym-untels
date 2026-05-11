package pe.com.untels.gym.rutina.dto;

import lombok.Data;
import pe.com.untels.gym.rutina.entities.Rutina;

import java.time.LocalDate;
@Data
public class RutinaDTO {
    private int Idrutina;
    private String nombre;
    private String descripcion;
    private LocalDate fechaCreacion;

    public RutinaDTO(Rutina rutina){
        this.Idrutina = rutina.getIdrutina();
        this.nombre = rutina.getNombre();
        this.descripcion = rutina.getDescripcion();
        this.fechaCreacion = rutina.getFechaCreacion();
    }
}
