package pe.com.untels.gym.categoriaimc.dtos;

import lombok.Data;
import pe.com.untels.gym.categoriaimc.entities.CategoriaImc;

@Data
public class CategoriaImcInsertDTO {
    private String nombre;
    private int rangoMin;
    private int rangoMax;

    public CategoriaImcInsertDTO(CategoriaImc categoriaImc) {
        this.nombre = categoriaImc.getNombre();
        this.rangoMax = categoriaImc.getRangoMax();
        this.rangoMin = categoriaImc.getRangoMin();
    }
}
