package pe.com.untels.gym.usuario.servicio;

import pe.com.untels.gym.usuario.dto.UsuarioResponseDTO;

import java.util.List;

public interface IUsuarioServicio {
    List<UsuarioResponseDTO> listarUsuarios();
}
