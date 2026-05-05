package pe.com.untels.gym.seguridad.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.com.untels.gym.seguridad.dtos.LoginRequest;
import pe.com.untels.gym.seguridad.dtos.RegistroRequest;
import pe.com.untels.gym.seguridad.dtos.TokenResponse;
import pe.com.untels.gym.seguridad.entities.Token;
import pe.com.untels.gym.seguridad.entities.Rol;
import pe.com.untels.gym.seguridad.entities.Usuario;
import pe.com.untels.gym.seguridad.repositories.RolRepositorio;
import pe.com.untels.gym.seguridad.repositories.TokenRepositorio;
import pe.com.untels.gym.seguridad.repositories.UsuarioRepositorio;

import java.util.List;

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
        Rol rol = rolRepositorio.findByPrivilegio(Rol.TipoRol.ROLE_USUARIO)
                .orElseThrow(() -> new RuntimeException("Rol no registrado"));
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
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getCorreoInstitucional(),
                        request.getContrasena()
                        )
        );
        Usuario usuario = usuarioRepositorio.findByCorreoInstitucional(request.getCorreoInstitucional())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        removerEstadoToken(usuario);
        final String jwtToken = jwtServicio.accessToken(usuario);
        final String jwtRefreshToken = jwtServicio.refreshToken(usuario);
        guardarToken(jwtRefreshToken,usuario);
        return TokenResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(jwtRefreshToken)
                .build();
    }

    public void removerEstadoToken(Usuario usuario) {
        List<Token> tokens = tokenRepositorio.findAllRevokedIsFalseByUsuarioId(usuario.getIdUsuario().toString())
                .orElseThrow();
        for (Token token : tokens) {
            token.setRemovido(true);
            token.setExpirado(true);
        }
        tokenRepositorio.saveAll(tokens);
    }

    public TokenResponse refresh(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Token Invalido");
        }
        final String jwtToken = authHeader.substring(7);
        final String correoInstitucional = jwtServicio.extraerUsername(jwtToken);
        if (correoInstitucional == null) {
            throw new IllegalArgumentException("Correo vacío");
        }
        Usuario usuario = usuarioRepositorio.findByCorreoInstitucional(correoInstitucional)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        if (!jwtServicio.isTokenValid(usuario,jwtToken)) {
            throw new IllegalArgumentException("Token invalido!!");
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
}
