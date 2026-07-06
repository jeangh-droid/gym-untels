package pe.com.untels.gym.ejercicio.servicesimplements;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.untels.gym.ejercicio.dtos.EjercicioInsertDTO;
import pe.com.untels.gym.ejercicio.entities.Ejercicio;
import pe.com.untels.gym.ejercicio.repositories.IEjercicioRepository;
import pe.com.untels.gym.ejercicio.servicesinterfaces.IEjercicioService;
import pe.com.untels.gym.grupoMuscular.entities.GrupoMuscular;
import pe.com.untels.gym.grupoMuscular.repositories.IGrupoMuscularRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EjercicioServiceImpl implements IEjercicioService {

    @Autowired
    private IEjercicioRepository ejercicioRepository;
    @Autowired
    private IGrupoMuscularRepository grupoMuscularRepository;
    @Override
    public List<Ejercicio> list() {
        return ejercicioRepository.findAll();
    }

    @Override
    public List<Ejercicio> findByGrupoMuscular(Integer idGrupo) {
        return ejercicioRepository.findByGrupoMuscular_IdGrupo(idGrupo);
    }

    @Override
    public Ejercicio insert(Ejercicio ejercicio) {
        return ejercicioRepository.save(ejercicio);
    }

    @Override
    public void delete(Integer id) {
        ejercicioRepository.deleteById(id);
    }

    @Override
    public Ejercicio findById(int id) {
        return ejercicioRepository.findById(id).orElseThrow();
    }

    @Override
    public Ejercicio update(int id, EjercicioInsertDTO ejercicioDTO) {
        // 1. Validar la existencia de la entidad base
        Optional<Ejercicio> ejercicioRegistrado = ejercicioRepository.findById(id);
        if (ejercicioRegistrado.isEmpty()) {
            throw new RuntimeException("Ejercicio no registrado");
        }

        Ejercicio ejercicioExistente = ejercicioRegistrado.get();

        // 2. Mapear campos simples mediante setters
        ejercicioExistente.setNombre(ejercicioDTO.getNombre());
        ejercicioExistente.setDescripcion(ejercicioDTO.getDescripcion());
        ejercicioExistente.setEquipo(ejercicioDTO.getEquipo());
        ejercicioExistente.setImagenUrl(ejercicioDTO.getImagenUrl());

        // 3. Manejar la relación (GrupoMuscular)
        if (ejercicioDTO.getIdGrupoMuscular() != null) {
            // Debes inyectar grupoMuscularRepository en tu clase Service
            GrupoMuscular grupo = grupoMuscularRepository.findById(ejercicioDTO.getIdGrupoMuscular())
                    .orElseThrow(() -> new RuntimeException("Grupo muscular no encontrado"));
            ejercicioExistente.setGrupoMuscular(grupo);
        }

        // 4. Guardar cambios en la base de datos
        return ejercicioRepository.save(ejercicioExistente);
    }

}