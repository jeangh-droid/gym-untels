package pe.com.untels.gym.usuario.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioDatosDTO {
    private Integer idUsuario;
    private String codigoUniversitario;
    private String nombreCompleto;
    private String correoInstitucional;
    private String genero;
    private LocalDate fechaNacimiento;
    private Double peso;
    private Double estatura;
    private Integer nivel;
    private String objetivo;
}
