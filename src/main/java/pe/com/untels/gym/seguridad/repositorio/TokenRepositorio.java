package pe.com.untels.gym.seguridad.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.com.untels.gym.seguridad.entidad.Token;

import java.util.List;
import java.util.Optional;

public interface TokenRepositorio extends JpaRepository<Token, Integer> {
    @Query("SELECT t FROM Token t WHERE (t.removido = false) AND t.usuario.idUsuario = :id")
    Optional<List<Token>> findAllRevokedIsFalseByUsuarioId(String id);
    Optional<Token> findByToken(String token);
}
