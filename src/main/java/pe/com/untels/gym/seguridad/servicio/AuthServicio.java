package pe.com.untels.gym.seguridad.servicio;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.com.untels.gym.seguridad.dto.LoginRequest;
import pe.com.untels.gym.seguridad.dto.RegistroRequest;
import pe.com.untels.gym.seguridad.dto.TokenResponse;
import pe.com.untels.gym.seguridad.modelo.RefreshToken;
import pe.com.untels.gym.seguridad.modelo.Rol;
import pe.com.untels.gym.seguridad.modelo.Usuario;
import pe.com.untels.gym.seguridad.repositorio.RolRepositorio;
import pe.com.untels.gym.seguridad.repositorio.TokenRepositorio;
import pe.com.untels.gym.seguridad.repositorio.UsuarioRepositorio;

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
        Rol rol = rolRepositorio.findByPrivilegio(Rol.TipoRol.USUARIO)
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
        List<RefreshToken> tokens = tokenRepositorio.findAllRevokedIsFalseByUsuarioId(usuario.getIdUsuario().toString())
                .orElseThrow();
        for (RefreshToken token : tokens) {
            token.setRemovido(true);
            token.setExpirado(true);
        }
        tokenRepositorio.saveAll(tokens);
    }

    public TokenResponse refresh(String authHeader) {
        return null;
    }

    public void guardarToken(String jwtRefreshToken, Usuario usuario) {
        RefreshToken refreshToken = RefreshToken.builder()
                .token(jwtRefreshToken)
                .expirado(false)
                .removido(false)
                .usuario(usuario)
                .build();
        tokenRepositorio.save(refreshToken);
    }
}
