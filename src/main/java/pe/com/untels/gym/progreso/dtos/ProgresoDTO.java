package pe.com.untels.gym.progreso.dtos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ProgresoDTO {
    private Integer idProgreso;
    private LocalDate fechaRegistro;
    private Double peso;
    private Double imc;
    private Integer caloriasQuemadas;
    private Integer minutosEntrenados;
}