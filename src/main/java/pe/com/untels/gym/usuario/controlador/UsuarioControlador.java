package pe.com.untels.gym.usuario.controlador;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.untels.gym.usuario.dto.UsuarioResponseDTO;
import pe.com.untels.gym.usuario.servicio.UsuarioServicio;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@EnableMethodSecurity
public class UsuarioControlador {
    final UsuarioServicio usuarioServicio;

    @GetMapping
    @PreAuthorize("hasRole('USUARIO')")
    public ResponseEntity<List<UsuarioResponseDTO>> obtenerUsuarios() {
        return ResponseEntity.ok(usuarioServicio.listarUsuarios());
    }
}
