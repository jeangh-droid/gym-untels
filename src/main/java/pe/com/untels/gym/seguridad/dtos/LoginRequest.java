package pe.com.untels.gym.seguridad.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "El correo es obligatorio")
    private String correoInstitucional;

    @NotBlank(message = "La contraseña es obligatoria")
    private String contrasena;
}