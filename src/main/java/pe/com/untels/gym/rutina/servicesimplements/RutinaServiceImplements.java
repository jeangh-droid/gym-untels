package pe.com.untels.gym.rutina.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.untels.gym.rutina.entities.Rutina;
import pe.com.untels.gym.rutina.repositories.IRutinaRepository;
import pe.com.untels.gym.rutina.servicesinterfaces.IRutinaService;

import java.util.List;
import java.util.Optional;

@Service
public class RutinaServiceImplements implements IRutinaService {
    @Autowired
    private IRutinaRepository rutR;

    @Override
    public List<Rutina>list() {
        return rutR.findAll();
    }

    @Override
    public Rutina insert(Rutina r) {
        return rutR.save(r);
    }

    @Override
    public Optional<Rutina> listid(int id) {
        return Optional.empty();
    }
}
