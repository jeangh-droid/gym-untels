package pe.com.untels.gym.progreso.dtos;

import lombok.Data;

@Data
public class ProgresoInsertDTO {
    private Integer idUsuario; // Identificador del usuario que registra
    private Double peso;
    private Double imc;
    private Integer caloriasQuemadas;
    private Integer minutosEntrenados;
}