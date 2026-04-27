package pe.com.untels.gym.seguridad.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.com.untels.gym.seguridad.modelo.RefreshToken;

import java.util.List;
import java.util.Optional;

public interface TokenRepositorio extends JpaRepository<RefreshToken, Integer> {
    @Query("SELECT t FROM RefreshToken t WHERE (t.removido = false) AND t.usuario.idUsuario = :id")
    Optional<List<RefreshToken>> findAllRevokedIsFalseByUsuarioId(String id);
    Optional<RefreshToken> findByToken(String token);
}
