package pe.com.untels.gym.seguridad.servicio;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pe.com.untels.gym.seguridad.modelo.Usuario;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtServicio {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long expiracion;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiracion;

    public String accessToken(Usuario usuario) {
        return tokenBuild(usuario, expiracion);
    }

    public String refreshToken(Usuario usuario) {
        return tokenBuild(usuario, refreshExpiracion);
    }

    public String tokenBuild(Usuario usuario, long expiracion) {
        return Jwts.builder()
                .id(usuario.getIdUsuario().toString())
                .subject(usuario.getCorreoInstitucional())
                .claim("nombre", usuario.getNombreCompleto())
                .claim("rol", usuario.getRol().getPrivilegio())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiracion))
                .signWith(secretKey())
                .compact();
    }

    private SecretKey secretKey() {
        byte[] key = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(key);
    }

}
