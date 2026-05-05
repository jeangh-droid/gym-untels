package pe.com.untels.gym.grupoMuscular.repositories;

import pe.com.untels.gym.grupoMuscular.entities.GrupoMuscular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGrupoMuscularRepository extends JpaRepository<GrupoMuscular, Integer> {
}