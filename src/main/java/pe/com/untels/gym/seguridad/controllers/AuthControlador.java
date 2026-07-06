package pe.com.untels.gym.seguridad.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pe.com.untels.gym.seguridad.dtos.LoginRequest;
import pe.com.untels.gym.seguridad.dtos.RegistroRequest;
import pe.com.untels.gym.seguridad.dtos.TokenResponse;
import pe.com.untels.gym.seguridad.services.AuthServicio;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthControlador {
    private final AuthServicio servicio;

    @PostMapping("/registro")
    public ResponseEntity<TokenResponse> registrar(@Valid @RequestBody RegistroRequest request) {
        return ResponseEntity.ok(servicio.registro(request));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(servicio.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return ResponseEntity.ok(servicio.refresh(authHeader));
    }

    // así ningún usuario puede cerrar la sesión de otro.
    @PostMapping("/logout")
    public ResponseEntity<?> logout(Authentication authentication) {
        servicio.logout(authentication.getName()); // correoInstitucional del usuario autenticado
        return ResponseEntity.ok("Logout exitoso, regrese pronto...");
    }
}