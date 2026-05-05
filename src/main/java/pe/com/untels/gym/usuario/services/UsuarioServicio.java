package pe.com.untels.gym.usuario.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.untels.gym.seguridad.entities.Usuario;
import pe.com.untels.gym.seguridad.repositories.UsuarioRepositorio;
import pe.com.untels.gym.usuario.dtos.UsuarioResponseDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServicio implements IUsuarioServicio{
    final UsuarioRepositorio usuarioRepositorio;

    @Override
    public List<UsuarioResponseDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        return usuarios.stream()
                .map(UsuarioResponseDTO::new)
                .toList();
    }

    public Usuario obtenerUsuario(Integer id) {
        return usuarioRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}
