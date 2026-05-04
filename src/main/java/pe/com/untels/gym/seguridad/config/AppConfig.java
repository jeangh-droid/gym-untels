package pe.com.untels.gym.seguridad.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pe.com.untels.gym.seguridad.entidad.Usuario;
import pe.com.untels.gym.seguridad.repositorio.UsuarioRepositorio;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    private final UsuarioRepositorio usuarioRepositorio;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
               final Usuario usuario = usuarioRepositorio.findByCorreoInstitucional(username)
                       .orElseThrow(() -> new UsernameNotFoundException("Usuario no Encontrado"));
               return User.builder()
                       .username(usuario.getCorreoInstitucional())
                       .password(usuario.getContrasena())
                       .build();
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
