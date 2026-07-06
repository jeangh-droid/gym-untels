package pe.com.untels.gym.seguridad.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import pe.com.untels.gym.seguridad.dtos.LoginRequest;
import pe.com.untels.gym.seguridad.dtos.RegistroRequest;
import pe.com.untels.gym.seguridad.dtos.TokenResponse;
import pe.com.untels.gym.seguridad.entities.Token;
import pe.com.untels.gym.seguridad.entities.Rol;
import pe.com.untels.gym.seguridad.entities.Usuario;
import pe.com.untels.gym.seguridad.repositories.RolRepositorio;
import pe.com.untels.gym.seguridad.repositories.TokenRepositorio;
import pe.com.untels.gym.seguridad.repositories.UsuarioRepositorio;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServicio {
    final UsuarioRepositorio usuarioRepositorio;
    final JwtServicio jwtServicio;
    final PasswordEncoder passwordEncoder;
    final RolRepositorio rolRepositorio;
    final TokenRepositorio tokenRepositorio;
    final AuthenticationManager authenticationManager;

    public TokenResponse registro(RegistroRequest request) {
        // validar correo duplicado ANTES de guardar, para devolver un 409 claro
        // en vez de dejar que reviente la restricción unique de la base de datos.
        if (usuarioRepositorio.existsByCorreoInstitucional(request.getCorreoInstitucional())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ya existe una cuenta registrada con ese correo institucional");
        }

        // El rol SIEMPRE es ROLE_USUARIO en el registro público. Nunca se lee del request.
        Rol rol = rolRepositorio.findByPrivilegio(Rol.TipoRol.ROLE_USUARIO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                        "El rol USUARIO no está configurado en la base de datos"));

        Usuario usuario = Usuario.builder()
                .codigoUniversitario(request.getCodigoUniversitario())
                .nombreCompleto(request.getNombreCompleto())
                .correoInstitucional(request.getCorreoInstitucional())
                .contrasena(passwordEncoder.encode(request.getContrasena()))
                .genero(request.getGenero())
                .fechaNacimiento(request.getFechaNacimiento())
                .peso(request.getPeso())
                .estatura(request.getEstatura())
                .nivel(request.getNivel())
                .objetivo(request.getObjetivo())
                .diasEntrenamiento(request.getDiasEntrenamiento())
                .build();
        usuario.setRol(rol);

        Usuario usuarioRegistrado = usuarioRepositorio.save(usuario);
        final String jwtToken = jwtServicio.accessToken(usuarioRegistrado);
        final String jwtRefreshToken = jwtServicio.refreshToken(usuarioRegistrado);
        guardarToken(jwtRefreshToken, usuarioRegistrado);
        return TokenResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(jwtRefreshToken)
                .build();
    }

    public TokenResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getCorreoInstitucional(),
                            request.getContrasena()
                    )
            );
        } catch (Exception e) {
            // Sin esto, credenciales inválidas pueden filtrar detalles internos
            // en vez de un mensaje uniforme al frontend.
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Correo o contraseña incorrectos");
        }

        Usuario usuario = usuarioRepositorio.findByCorreoInstitucional(request.getCorreoInstitucional())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        removerEstadoToken(usuario);
        final String jwtToken = jwtServicio.accessToken(usuario);
        final String jwtRefreshToken = jwtServicio.refreshToken(usuario);
        guardarToken(jwtRefreshToken, usuario);
        return TokenResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(jwtRefreshToken)
                .build();
    }

    public void removerEstadoToken(Usuario usuario) {
        // Si el usuario no tiene tokens previos vigentes (ej. primer login
        // de una cuenta migrada manualmente), simplemente no hay nada que revocar.
        List<Token> tokens = tokenRepositorio
                .findAllRevokedIsFalseByUsuarioId(usuario.getIdUsuario().toString())
                .orElse(Collections.emptyList());

        if (tokens.isEmpty()) return;

        for (Token token : tokens) {
            token.setRemovido(true);
            token.setExpirado(true);
        }
        tokenRepositorio.saveAll(tokens);
    }

    public TokenResponse refresh(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token inválido");
        }
        final String jwtToken = authHeader.substring(7);
        final String correoInstitucional = jwtServicio.extraerUsername(jwtToken);
        if (correoInstitucional == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Correo vacío");
        }
        Usuario usuario = usuarioRepositorio.findByCorreoInstitucional(correoInstitucional)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        if (!jwtServicio.isTokenValid(usuario, jwtToken)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token inválido");
        }
        removerEstadoToken(usuario);
        final String accessToken = jwtServicio.accessToken(usuario);
        final String refreshToken = jwtServicio.refreshToken(usuario);
        guardarToken(refreshToken, usuario);
        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public void guardarToken(String jwtRefreshToken, Usuario usuario) {
        Token token = Token.builder()
                .token(jwtRefreshToken)
                .expirado(false)
                .removido(false)
                .usuario(usuario)
                .build();
        tokenRepositorio.save(token);
    }

    // Recibe el correo del usuario autenticado (sacado del token en el controlador),
    public boolean logout(String correoInstitucional) {
        Usuario usuario = usuarioRepositorio.findByCorreoInstitucional(correoInstitucional)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no registrado en bd"));

        List<Token> tokens = tokenRepositorio
                .findAllRevokedIsFalseByUsuarioId(usuario.getIdUsuario().toString())
                .orElse(Collections.emptyList());

        tokens.forEach(token -> {
            token.setExpirado(true);
            token.setRemovido(true);
        });
        tokenRepositorio.saveAll(tokens);
        return true;
    }
}