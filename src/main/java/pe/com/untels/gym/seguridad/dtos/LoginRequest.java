package pe.com.untels.gym.seguridad.dtos;

import lombok.Data;

@Data
public class LoginRequest {
    private String correoInstitucional;
    private String contrasena;
}
