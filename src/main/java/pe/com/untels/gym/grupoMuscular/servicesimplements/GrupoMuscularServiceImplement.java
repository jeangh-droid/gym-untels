package pe.com.untels.gym.grupoMuscular.servicesimplements;

import pe.com.untels.gym.grupoMuscular.entities.GrupoMuscular;
import pe.com.untels.gym.grupoMuscular.repositories.IGrupoMuscularRepository;
import pe.com.untels.gym.grupoMuscular.servicesinterfaces.IGrupoMuscularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GrupoMuscularServiceImplement implements IGrupoMuscularService {
    @Autowired
    private IGrupoMuscularRepository gmR;

    @Override
    public List<GrupoMuscular> list() {
        return gmR.findAll();
    }

    @Override
    public GrupoMuscular insert(GrupoMuscular gm) {
        return gmR.save(gm);
    }

    @Override
    public Optional<GrupoMuscular> listId(int id) {
        return gmR.findById(id);
    }
}