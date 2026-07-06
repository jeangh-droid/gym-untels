package pe.com.untels.gym.ejercicio.servicesinterfaces;

import pe.com.untels.gym.ejercicio.dtos.EjercicioDTO;
import pe.com.untels.gym.ejercicio.dtos.EjercicioInsertDTO;
import pe.com.untels.gym.ejercicio.entities.Ejercicio;
import java.util.List;

public interface IEjercicioService {
    List<Ejercicio> list();
    List<Ejercicio> findByGrupoMuscular(Integer idGrupo);
    Ejercicio insert(EjercicioInsertDTO ejercicio);
    void delete(Integer id);
    Ejercicio findById(int id);
    Ejercicio update(int id, EjercicioInsertDTO ejercicio);
}