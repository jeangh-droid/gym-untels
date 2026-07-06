package pe.com.untels.gym.progreso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.untels.gym.progreso.entities.HistorialProgreso;
import pe.com.untels.gym.progreso.repositories.IProgresoRepository;
import pe.com.untels.gym.progreso.servicesinterfaces.IProgresoService;

import java.util.List;

@Service
public class ProgresoServiceImpl implements IProgresoService {

    @Autowired
    private IProgresoRepository progresoRepository;

    @Override
    public List<HistorialProgreso> obtenerPorUsuario(Integer idUsuario) {
        return progresoRepository.findByUsuario_IdUsuarioOrderByFechaRegistroAsc(idUsuario);
    }

    @Override
    public HistorialProgreso insert(HistorialProgreso progreso) {
        return progresoRepository.save(progreso);
    }
}