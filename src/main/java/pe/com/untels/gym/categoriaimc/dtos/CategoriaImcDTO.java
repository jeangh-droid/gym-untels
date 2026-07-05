package pe.com.untels.gym.categoriaimc.dtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CategoriaImcDTO {
    private String nombre;
    private int rangoMin;
    private int rangoMax;
}
