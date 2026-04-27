package pe.com.untels.gym.seguridad.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class RegistroRequest {
    private String codigoUniversitario;
    private String nombreCompleto;
    private String correoInstitucional;
    private String contrasena;
    private String genero;
    private LocalDate fechaNacimiento;
    private Double peso;
    private Double estatura;
    private Integer nivel;
    private String objetivo;
    private Integer diasEntrenamiento;
}
