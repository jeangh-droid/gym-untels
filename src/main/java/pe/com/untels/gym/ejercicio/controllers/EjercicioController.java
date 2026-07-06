package pe.com.untels.gym.ejercicio.controllers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.com.untels.gym.ejercicio.dtos.EjercicioDTO;
import pe.com.untels.gym.ejercicio.dtos.EjercicioInsertDTO;
import pe.com.untels.gym.ejercicio.entities.Ejercicio;
import pe.com.untels.gym.ejercicio.servicesinterfaces.IEjercicioService;
import java.util.List;

@RestController
@RequestMapping("/api/ejercicios")
public class EjercicioController {
    @Autowired
    private IEjercicioService ejercicioService;

    @GetMapping("/lista")
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMIN')")
    public ResponseEntity<List<EjercicioDTO>> lista() {
        ModelMapper modelMapper = new ModelMapper();
        List<EjercicioDTO> lista = ejercicioService.list().stream()
                .map(ejercicio -> modelMapper.map(ejercicio, EjercicioDTO.class))
                .toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/grupo/{idGrupo}")
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMIN')")
    public ResponseEntity<List<EjercicioDTO>> buscarPorGrupo(@PathVariable Integer idGrupo) {
        ModelMapper modelMapper = new ModelMapper();
        List<EjercicioDTO> lista = ejercicioService.findByGrupoMuscular(idGrupo).stream()
                .map(ejercicio -> modelMapper.map(ejercicio, EjercicioDTO.class))
                .toList();
        return ResponseEntity.ok(lista);
    }

    // FIX: agregado para precargar formulario de edición
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMIN')")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        Ejercicio ejercicio = ejercicioService.findById(id);
        if (ejercicio == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ejercicio no encontrado");
        }
        ModelMapper modelMapper = new ModelMapper();
        return ResponseEntity.ok(modelMapper.map(ejercicio, EjercicioDTO.class));
    }

    @PostMapping("/nuevo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EjercicioDTO> registrar(@RequestBody EjercicioInsertDTO request) {
        ModelMapper modelMapper = new ModelMapper();
        Ejercicio ejercicio = ejercicioService.insert(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(ejercicio,EjercicioDTO.class));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody EjercicioInsertDTO request) {
        Ejercicio existente = ejercicioService.findById(id);
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ejercicio no encontrado");
        }
        EjercicioDTO response = modelMapper().map(ejercicioService.update(id, request), EjercicioDTO.class);
        return ResponseEntity.ok(response);
    }

    private ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        ejercicioService.delete(id);
        return ResponseEntity.ok("Ejercicio eliminado correctamente");
    }
}