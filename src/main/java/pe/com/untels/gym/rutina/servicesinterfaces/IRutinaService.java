package pe.com.untels.gym.rutina.servicesinterfaces;

import pe.com.untels.gym.rutina.entities.Rutina;

import java.util.List;
import java.util.Optional;

public interface IRutinaService {
    public List<Rutina>list();
    public Rutina insert(Rutina r);
    public Optional<Rutina> listid(int id);
}
