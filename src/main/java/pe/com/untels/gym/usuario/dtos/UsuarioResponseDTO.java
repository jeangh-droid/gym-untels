package pe.com.untels.gym.usuario.dtos;

import lombok.Data;
import pe.com.untels.gym.seguridad.entities.Usuario;

@Data
public class UsuarioResponseDTO {
    private String correoInstitucional;
    private String nombreCompleto;
    private Double peso;
    private Double estatura;

    public UsuarioResponseDTO(Usuario usuario) {
        this.correoInstitucional = usuario.getCorreoInstitucional();
        this.nombreCompleto = usuario.getNombreCompleto();
        this.peso = usuario.getPeso();
        this.estatura = usuario.getEstatura();
    }
}
