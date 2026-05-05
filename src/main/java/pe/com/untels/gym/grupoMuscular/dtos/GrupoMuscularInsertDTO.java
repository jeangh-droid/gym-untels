package pe.com.untels.gym.grupoMuscular.dtos;

import lombok.Data;
import pe.com.untels.gym.grupoMuscular.entities.GrupoMuscular;

@Data
public class GrupoMuscularInsertDTO {

    private String nombre;
    private String descripcion;
    private String imagenGrupo;
    private String colorIndicador;

    public GrupoMuscularInsertDTO(GrupoMuscular grupoMuscular) {
        this.nombre = grupoMuscular.getNombre();
        this.descripcion = grupoMuscular.getDescripcion();
        this.imagenGrupo = grupoMuscular.getImagenGrupo();
        this.colorIndicador = grupoMuscular.getColorIndicador();
    }
}