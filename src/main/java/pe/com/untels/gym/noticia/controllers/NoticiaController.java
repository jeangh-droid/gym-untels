package pe.com.untels.gym.noticia.controllers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.com.untels.gym.noticia.dtos.NoticiaDTO;
import pe.com.untels.gym.noticia.dtos.NoticiaInsertDTO;
import pe.com.untels.gym.noticia.entities.Noticia;
import pe.com.untels.gym.noticia.servicesinterfaces.INoticiaService;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/noticias")
public class NoticiaController {
    @Autowired
    private INoticiaService noticiaService;

    @GetMapping("/lista")
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMIN')")
    public ResponseEntity<List<NoticiaDTO>> lista() {
        ModelMapper modelMapper = new ModelMapper();
        List<NoticiaDTO> lista = noticiaService.list().stream()
                .map(noticia -> modelMapper.map(noticia, NoticiaDTO.class))
                .toList();
        return ResponseEntity.ok(lista);
    }

    // FIX: agregado para poder precargar el formulario de edición en el frontend
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMIN')")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        Noticia noticia = noticiaService.findById(id);
        if (noticia == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Noticia no encontrada");
        }
        ModelMapper modelMapper = new ModelMapper();
        return ResponseEntity.ok(modelMapper.map(noticia, NoticiaDTO.class));
    }

    @PostMapping("/nuevo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<NoticiaDTO> registrar(@RequestBody NoticiaInsertDTO request) {
        ModelMapper modelMapper = new ModelMapper();
        Noticia noticia = modelMapper.map(request, Noticia.class);
        noticia.setFechaPublicacion(LocalDate.now());
        NoticiaDTO response = modelMapper.map(noticiaService.insert(noticia), NoticiaDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // FIX: agregado — antes solo se podía crear o borrar, no editar
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody NoticiaInsertDTO request) {
        Noticia existente = noticiaService.findById(id);
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Noticia no encontrada");
        }
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(request, existente); // sobrescribe campos del insert DTO sobre la entidad existente
        NoticiaDTO response = modelMapper.map(noticiaService.update(existente), NoticiaDTO.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        noticiaService.delete(id);
        return ResponseEntity.ok("Noticia eliminada correctamente");
    }
}