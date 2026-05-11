package pe.com.untels.gym.rutina.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class RutinaInsertDTO {
    private int Idrutina;
    private String nombre;
    private String descripcion;
    private LocalDate fechaCreacion;

    public int getIdrutina() {
        return Idrutina;
    }

    public void setIdrutina(int idrutina) {
        Idrutina = idrutina;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
