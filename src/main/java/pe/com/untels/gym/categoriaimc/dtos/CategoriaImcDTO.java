package pe.com.untels.gym.categoriaimc.dtos;

public class CategoriaImcDTO {

    private String nombre;
    private int rangoMin;
    private int rangoMax;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRangoMin() {
        return rangoMin;
    }

    public void setRangoMin(int rangoMin) {
        this.rangoMin = rangoMin;
    }

    public int getRangoMax() {
        return rangoMax;
    }

    public void setRangoMax(int rangoMax) {
        this.rangoMax = rangoMax;
    }
}
