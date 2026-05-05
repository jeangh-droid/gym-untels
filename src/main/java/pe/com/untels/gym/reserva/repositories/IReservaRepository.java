package pe.com.untels.gym.reserva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.com.untels.gym.reserva.entities.Reserva;

import java.util.List;

@Repository
public interface IReservaRepository extends JpaRepository<Reserva,Integer> {

    // HUF06: Historial de reservas
    @Query("SELECT r FROM Reserva r WHERE r.usuario.idUsuario = :idUsuario " +
            "AND r.estado IN ('ASISTIDO','CANCELADO','NO_ASISTIO') " +
            "ORDER BY r.fechaReserva DESC, r.horaInicio DESC")
    List<Reserva> findHistorialByUsuario(int idUsuario,List<Reserva.EstadoReserva> estados);
}
