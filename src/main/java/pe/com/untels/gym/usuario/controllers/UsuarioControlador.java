package pe.com.untels.gym.usuario.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.untels.gym.usuario.dtos.UsuarioResponseDTO;
import pe.com.untels.gym.usuario.services.UsuarioServicio;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioControlador {
    final UsuarioServicio usuarioServicio;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> obtenerUsuarios() {
        return ResponseEntity.ok(usuarioServicio.listarUsuarios());
    }
}
