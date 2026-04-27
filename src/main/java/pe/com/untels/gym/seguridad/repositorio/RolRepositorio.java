package pe.com.untels.gym.seguridad.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.untels.gym.seguridad.modelo.Rol;

import java.util.Optional;

@Repository
public interface RolRepositorio extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByPrivilegio(Rol.TipoRol tipoRol);
}
