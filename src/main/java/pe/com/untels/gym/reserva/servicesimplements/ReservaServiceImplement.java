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
}
