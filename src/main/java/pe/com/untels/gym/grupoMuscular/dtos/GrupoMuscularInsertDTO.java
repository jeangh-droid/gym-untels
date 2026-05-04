package pe.com.untels.gym.grupoMuscular.dtos;

public class GrupoMuscularInsertDTO {
    private int idGrupo;
    private String nombre;
    private String descripcion;
    private String imagenGrupo;
    private String colorIndicador;

    // Getters y Setters completos
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