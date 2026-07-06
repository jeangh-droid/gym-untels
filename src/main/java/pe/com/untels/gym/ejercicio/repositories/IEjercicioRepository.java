package pe.com.untels.gym.ejercicio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.untels.gym.ejercicio.entities.Ejercicio;

import java.util.List;

@Repository
public interface IEjercicioRepository extends JpaRepository<Ejercicio, Integer> {
    List<Ejercicio> findByGrupoMuscular_IdGrupo(Integer idGrupo);
}