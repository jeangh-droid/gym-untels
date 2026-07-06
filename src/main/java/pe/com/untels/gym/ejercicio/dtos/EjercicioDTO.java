package pe.com.untels.gym.ejercicio.dtos;

import lombok.Data;

@Data
public class EjercicioDTO {
    private Integer idEjercicio;
    private String nombre;
    private String descripcion;
    private String equipo;
    private String imagenUrl;
    private Integer idGrupoMuscular; // Mapeado manualmente si es necesario o por ModelMapper
}