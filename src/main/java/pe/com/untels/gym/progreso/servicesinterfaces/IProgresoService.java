package pe.com.untels.gym.progreso.servicesinterfaces;

import pe.com.untels.gym.progreso.entities.HistorialProgreso;
import java.util.List;

public interface IProgresoService {
    List<HistorialProgreso> obtenerPorUsuario(Integer idUsuario);
    HistorialProgreso insert(HistorialProgreso progreso);
}