package pe.com.untels.gym.reserva.servicesinterfaces;

import java.util.List;
import java.util.Optional;
import pe.com.untels.gym.reserva.entities.Reserva;

public interface IReservaService {
    List<Reserva> list();

    Reserva insert(Reserva r);

    Optional<Reserva> listId(int id);

    List<Reserva> historialReserva(int idUsuario);

    void cambiarEstado(int idEstado, Reserva.EstadoReserva estadoReserva);
}
