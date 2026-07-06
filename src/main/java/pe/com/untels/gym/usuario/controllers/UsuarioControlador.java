package pe.com.untels.gym.usuario.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pe.com.untels.gym.seguridad.repositories.TokenRepositorio;
import pe.com.untels.gym.usuario.dtos.UsuarioActualizarDTO;
import pe.com.untels.gym.usuario.dtos.UsuarioDatosDTO;
import pe.com.untels.gym.usuario.dtos.UsuarioResponseDTO;
import pe.com.untels.gym.usuario.services.UsuarioServicio;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@EnableMethodSecurity
public class UsuarioControlador {
    private final UsuarioServicio usuarioServicio;
    private final TokenRepositorio tokenRepositorio;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioResponseDTO>> obtenerUsuarios() {
        return ResponseEntity.ok(usuarioServicio.listarUsuarios());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioDatosDTO> obtenerDatosPerfilPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioServicio.perfil(id));
    }

    @GetMapping("/perfil")
    @PreAuthorize("hasAnyRole('USUARIO','ADMIN')")
    public ResponseEntity<UsuarioDatosDTO> obtenerMiPerfil(Authentication authentication) {
        Integer idUsuarioAutenticado = usuarioServicio.obtenerIdPorCorreo(authentication.getName());
        return ResponseEntity.ok(usuarioServicio.perfil(idUsuarioAutenticado));
    }

    @PutMapping("/perfil")
    @PreAuthorize("hasAnyRole('USUARIO','ADMIN')")
    public ResponseEntity<UsuarioDatosDTO> actualizarMiPerfil(
            Authentication authentication,
            @RequestBody UsuarioActualizarDTO request) {
        Integer idUsuarioAutenticado = usuarioServicio.obtenerIdPorCorreo(authentication.getName());
        return ResponseEntity.ok(usuarioServicio.actualizarPerfil(idUsuarioAutenticado, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Integer id) {
        tokenRepositorio.deleteAll(tokenRepositorio.findAllByUsuario_IdUsuario(id));

        boolean eliminado = usuarioServicio.eliminarUsuario(id);
        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo eliminar al usuario");
        }
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }
}