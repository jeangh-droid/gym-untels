package pe.com.untels.gym.usuario.services;

import pe.com.untels.gym.usuario.dtos.UsuarioDatosDTO;
import pe.com.untels.gym.usuario.dtos.UsuarioResponseDTO;

import java.util.List;

public interface IUsuarioServicio {
    List<UsuarioResponseDTO> listarUsuarios();
    UsuarioDatosDTO perfil(Integer id);
}

