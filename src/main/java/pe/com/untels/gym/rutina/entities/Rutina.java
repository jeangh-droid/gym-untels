package pe.com.untels.gym.rutina.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name="Rutina")

public class Rutina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Idrutina;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario IdUsuario;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "fechaCreacion", nullable = false)
    private LocalDate fechaCreacion;

    public Rutina() {
    }

    public Rutina(LocalDate fechaCreacion, String descripcion, String nombre, Usuario idUsuario, int idrutina) {
        this.fechaCreacion = fechaCreacion;
        this.descripcion = descripcion;
        this.nombre = nombre;
        IdUsuario = idUsuario;
        Idrutina = idrutina;
    }

    public int getIdrutina() {
        return Idrutina;
    }

    public void setIdrutina(int idrutina) {
        Idrutina = idrutina;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Usuario getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        IdUsuario = idUsuario;
    }
}
