package pe.com.untels.gym.reserva.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.untels.gym.reserva.modelo.Reserva;
import pe.com.untels.gym.reserva.repositories.IReservaRepository;
import pe.com.untels.gym.reserva.servicesinterfaces.IReservaService;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaServiceImplement implements IReservaService {
    @Autowired
    private IReservaRepository rR;

    @Override
    public List<Reserva> list() {
        return rR.findAll();
    }

    @Override
    public Reserva insert(Reserva r) {
        return rR.save(r);
    }

    @Override
    public Optional<Reserva> listId(int id) {
        return rR.findById(id);
    }

    public List<Reserva> historialReserva(int idUsuario){
    //Falta agregar la logica de NO_ASISTIO (el sistema lo tiene que hacer)
        return rR.findHistorialByUsuario(idUsuario,
                List.of(
                Reserva.EstadoReserva.ASISTIDO,
                Reserva.EstadoReserva.CANCELADO,
                Reserva.EstadoReserva.NO_ASISTIO
            )
        );
    }
}
