package pe.com.untels.gym.reserva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.untels.gym.reserva.modelo.Reserva;

@Repository
public interface IReservaRepository extends JpaRepository<Reserva,Integer>
{
}
