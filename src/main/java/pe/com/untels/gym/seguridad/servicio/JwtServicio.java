package pe.com.untels.gym.seguridad.servicio;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pe.com.untels.gym.seguridad.entidad.Usuario;

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

    //En nuestro caso será extraer el correo institucional, pero igual lo llamaremos
    //username ya que es más fácil identificar
    public String extraerUsername(String jwtToken) {
        final Claims jwtClaim = Jwts.parser()
                .verifyWith(secretKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
        return jwtClaim.getSubject();
    }

    public Date extraerExpiracion(String jwtToken) {
        final Claims jwtClaim = Jwts.parser()
                .verifyWith(secretKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
        return jwtClaim.getExpiration();
    }

    public String extraerRol(String jwtToken) {
        final Claims claims = Jwts.parser()
                .verifyWith(secretKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
        return claims.get("rol").toString();
    }

    public boolean isTokenValid(Usuario usuario, String jwtToken) {
        final String correoInstitucional = extraerUsername(jwtToken);
        return correoInstitucional.equals(usuario.getCorreoInstitucional()) && !isTokenExpired(jwtToken);
    }

    private boolean isTokenExpired(String jwtToken) {
        return extraerExpiracion(jwtToken).before(new Date());
    }


}
