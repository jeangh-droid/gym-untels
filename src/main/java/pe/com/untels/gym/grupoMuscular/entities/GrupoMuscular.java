package pe.com.untels.gym.grupoMuscular.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "GrupoMuscular")
public class GrupoMuscular {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGrupo;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "imagenGrupo")
    private String imagenGrupo;

    @Column(name = "colorIndicador")
    private String colorIndicador;

    public GrupoMuscular() {
    }

    public GrupoMuscular(int idGrupo, String nombre, String descripcion, String imagenGrupo, String colorIndicador) {
        this.idGrupo = idGrupo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenGrupo = imagenGrupo;
        this.colorIndicador = colorIndicador;
    }

    // Getters y Setters (Sigue el mismo patrón que ICategoriaImcService)
    public int getIdGrupo() { return idGrupo; }
    public void setIdGrupo(int idGrupo) { this.idGrupo = idGrupo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getImagenGrupo() { return imagenGrupo; }
    public void setImagenGrupo(String imagenGrupo) { this.imagenGrupo = imagenGrupo; }
    public String getColorIndicador() { return colorIndicador; }
    public void setColorIndicador(String colorIndicador) { this.colorIndicador = colorIndicador; }
}