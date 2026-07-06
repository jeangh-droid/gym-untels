package pe.com.untels.gym.grupoMuscular.dtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GrupoMuscularInsertDTO {
    private String nombre;
    private String descripcion;
    private String imagenGrupo;
    private String colorIndicador;
}
