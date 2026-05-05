package pe.com.untels.gym.reserva.servicesinterfaces;

import pe.com.untels.gym.reserva.entities.Reserva;

import java.util.List;
import java.util.Optional;

public interface IReservaService {
    public List<Reserva> list();
    public Reserva insert(Reserva r);
    public Optional<Reserva> listId(int id);
    public List<Reserva> historialReserva(int idUsuario);
}
