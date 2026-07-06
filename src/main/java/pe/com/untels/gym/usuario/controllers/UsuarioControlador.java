package pe.com.untels.gym.usuario.controllers;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import pe.com.untels.gym.usuario.dtos.UsuarioDatosDTO;
import pe.com.untels.gym.usuario.dtos.UsuarioResponseDTO;
import pe.com.untels.gym.usuario.services.UsuarioServicio;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@EnableMethodSecurity
public class UsuarioControlador {
    final UsuarioServicio usuarioServicio;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioResponseDTO>> obtenerUsuarios() {
        return ResponseEntity.ok(usuarioServicio.listarUsuarios());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioDatosDTO> obtenerDatosPerfil(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioServicio.perfil(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Integer id) {
        // FIX: antes se llamaba eliminarUsuario(id) DOS veces -> intentaba borrar dos veces
        // y devolvía el resultado (booleano) del segundo intento como body.
        boolean eliminado = usuarioServicio.eliminarUsuario(id);
        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo eliminar al usuario");
        }
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }
}