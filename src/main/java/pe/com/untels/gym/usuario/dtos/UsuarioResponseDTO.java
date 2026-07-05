package pe.com.untels.gym.usuario.dtos;
import lombok.Data;

@Data
public class UsuarioResponseDTO {
    private Integer idUsuario; // FIX: sin esto el frontend no puede armar DELETE /usuarios/{id}
    private String correoInstitucional;
    private String nombreCompleto;
    private Double peso;
    private Double estatura;
}