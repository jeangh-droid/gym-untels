package pe.com.untels.gym.ejercicio.dtos;

import lombok.Data;

@Data
public class EjercicioInsertDTO {
    private String nombre;
    private String descripcion;
    private String equipo;
    private String imagenUrl;
    private Integer idGrupoMuscular;
}