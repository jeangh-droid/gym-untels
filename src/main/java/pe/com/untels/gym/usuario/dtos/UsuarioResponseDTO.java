package pe.com.untels.gym.usuario.dtos;
import lombok.Data;

@Data
public class UsuarioResponseDTO {
    private Integer idUsuario;
    private String correoInstitucional;
    private String nombreCompleto;
    private Double peso;
    private Double estatura;
}