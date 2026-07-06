package pe.com.untels.gym.rutina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.untels.gym.rutina.entities.Rutina;

@Repository
public interface IRutinaRepository extends JpaRepository<Rutina,Integer> {
}
