package pe.com.untels.gym.grupoMuscular.servicesinterfaces;

import pe.com.untels.gym.grupoMuscular.entities.GrupoMuscular;
import java.util.List;
import java.util.Optional;

public interface IGrupoMuscularService {
    public List<GrupoMuscular> list();
    public GrupoMuscular insert(GrupoMuscular gm);
    public Optional<GrupoMuscular> listId(int id);
}