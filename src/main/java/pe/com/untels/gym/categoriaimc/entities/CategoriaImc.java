package pe.com.untels.gym.categoriaimc.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "CategoriaIMC")
public class CategoriaImc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategoria;

    @Column(name = "nombre", length = 50, nullable = false, unique = true)
    private String nombre;

    @Column(name ="rangoMin",nullable = false)
    private int rangoMin;

    @Column(name = "rangoMax",nullable = false)
    private int rangoMax;

    public CategoriaImc() {
    }

    public CategoriaImc(int idCategoria, String nombre, int rangoMin, int rangoMax) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.rangoMin = rangoMin;
        this.rangoMax = rangoMax;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

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
