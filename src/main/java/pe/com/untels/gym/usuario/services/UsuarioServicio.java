package pe.com.untels.gym.usuario.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pe.com.untels.gym.seguridad.entities.Usuario;
import pe.com.untels.gym.seguridad.repositories.UsuarioRepositorio;
import pe.com.untels.gym.usuario.dtos.UsuarioDatosDTO;
import pe.com.untels.gym.usuario.dtos.UsuarioResponseDTO;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServicio implements IUsuarioServicio{
    final UsuarioRepositorio usuarioRepositorio;

    @Override
    public List<UsuarioResponseDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        ModelMapper modelMapper = new ModelMapper();
        return usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioResponseDTO.class))
                .toList();
    }

    @Override
    public UsuarioDatosDTO perfil(Integer id) {
        Usuario usuario = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuario, UsuarioDatosDTO.class);
    }

    public Usuario obtenerUsuario(Integer id) {
        return usuarioRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Boolean eliminarUsuario(Integer id) {
        if (usuarioRepositorio.findById(id).isEmpty()) {
            return false;
        }
        usuarioRepositorio.deleteById(id);
        return true;
    }
}
