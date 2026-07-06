package pe.com.untels.gym.progreso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.untels.gym.progreso.entities.HistorialProgreso;

import java.util.List;

@Repository
public interface IProgresoRepository extends JpaRepository<HistorialProgreso, Integer> {
    // Ordenar por fecha ascendente es ideal para pintar gráficos de líneas en Angular
    List<HistorialProgreso> findByUsuario_IdUsuarioOrderByFechaRegistroAsc(Integer idUsuario);
}