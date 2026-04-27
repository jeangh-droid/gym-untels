package pe.com.untels.gym.seguridad.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pe.com.untels.gym.seguridad.modelo.RefreshToken;
import pe.com.untels.gym.seguridad.modelo.Usuario;
import pe.com.untels.gym.seguridad.repositorio.TokenRepositorio;
import pe.com.untels.gym.seguridad.repositorio.UsuarioRepositorio;
import pe.com.untels.gym.seguridad.servicio.JwtServicio;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    final JwtServicio jwtServicio;
    final TokenRepositorio tokenRepositorio;
    final UserDetailsService userDetailsService;
    final UsuarioRepositorio usuarioRepositorio;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().contains("/auth")) {
            filterChain.doFilter(request,response);
            return;
        }
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request,response);
            return;
        }
        final String jwtToken = authHeader.substring(7);
        final String correoInstitucional = jwtServicio.extraerUsername(jwtToken);

        if (correoInstitucional == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request,response);
            return;
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(correoInstitucional);
        final Optional<Usuario> usuario = usuarioRepositorio.findByCorreoInstitucional(userDetails.getUsername());

        if (usuario.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        final boolean isTokenValid = jwtServicio.isTokenValid(usuario.get(),jwtToken);
        if (!isTokenValid) {
            filterChain.doFilter(request,response);
            return;
        }

        final var authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );

        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request,response);
    }
}
