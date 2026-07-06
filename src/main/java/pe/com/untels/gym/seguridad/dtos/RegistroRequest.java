package pe.com.untels.gym.seguridad.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegistroRequest {

    @Size(max = 50, message = "El código universitario es demasiado largo")
    private String codigoUniversitario; // opcional, no se pide en el formulario de registro

    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(min = 3, max = 150, message = "El nombre debe tener entre 3 y 150 caracteres")
    private String nombreCompleto;

    @NotBlank(message = "El correo institucional es obligatorio")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@untels\\.edu\\.pe$",
            message = "El correo debe pertenecer al dominio @untels.edu.pe"
    )
    private String correoInstitucional;

    @NotBlank(message = "La contraseña es obligatoria")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!.*_-]).{6,}$",
            message = "La contraseña debe tener al menos 6 caracteres, una mayúscula, una minúscula, un número y un carácter especial"
    )
    private String contrasena;

    @NotBlank(message = "El género es obligatorio")
    @Pattern(regexp = "^(Femenino|Masculino)$", message = "Género inválido")
    private String genero;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser anterior a hoy")
    private LocalDate fechaNacimiento;

    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso debe ser mayor a 0")
    @DecimalMax(value = "400.0", message = "Ingresa un peso válido")
    private Double peso;

    @NotNull(message = "La estatura es obligatoria")
    @Positive(message = "La estatura debe ser mayor a 0")
    @DecimalMax(value = "2.5", message = "Ingresa una estatura válida en metros (ej. 1.75)")
    private Double estatura;

    @Min(value = 1, message = "Nivel inválido")
    @Max(value = 3, message = "Nivel inválido")
    private Integer nivel;

    private String objetivo;

    private Integer diasEntrenamiento;
}