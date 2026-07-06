package pe.com.untels.gym.grupoMuscular.controllers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import pe.com.untels.gym.grupoMuscular.dtos.GrupoMuscularDTO;
import pe.com.untels.gym.grupoMuscular.dtos.GrupoMuscularInsertDTO;
import pe.com.untels.gym.grupoMuscular.entities.GrupoMuscular;
import pe.com.untels.gym.grupoMuscular.servicesinterfaces.IGrupoMuscularService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/grupoMuscular")
@EnableMethodSecurity
public class GrupoMuscularController {
    @Autowired
    private IGrupoMuscularService grupoMuscularService;

    @GetMapping("/lista")
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMIN')")
    public ResponseEntity<List<GrupoMuscularDTO>> lista() {
        ModelMapper modelMapper = new ModelMapper();
        List<GrupoMuscularDTO> lista = grupoMuscularService.list()
                .stream().map(grupoMuscular -> modelMapper.map(grupoMuscular, GrupoMuscularDTO.class))
                .toList();
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/nuevo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GrupoMuscularDTO> registrar(@RequestBody GrupoMuscularInsertDTO request) {
        ModelMapper modelMapper = new ModelMapper();
        GrupoMuscular grupoMuscular = modelMapper.map(request, GrupoMuscular.class);
        GrupoMuscularDTO response = modelMapper.map(grupoMuscularService.insert(grupoMuscular), GrupoMuscularDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        Optional<GrupoMuscular> grupoMuscularOptional = grupoMuscularService.listId(id);
        if (grupoMuscularOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Grupo muscular no encontrado");
        }
        ModelMapper modelMapper = new ModelMapper();
        GrupoMuscularDTO response = modelMapper.map(grupoMuscularOptional.get(), GrupoMuscularDTO.class);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> actualizar(@PathVariable int id, @RequestBody GrupoMuscularInsertDTO request) {
        Optional<GrupoMuscular> existente = grupoMuscularService.listId(id);
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Grupo muscular no encontrado");
        }
        ModelMapper modelMapper = new ModelMapper();
        GrupoMuscular actualizado = existente.get();
        modelMapper.map(request, actualizado);
        GrupoMuscularDTO response = modelMapper.map(grupoMuscularService.update(actualizado), GrupoMuscularDTO.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        grupoMuscularService.delete(id);
        return ResponseEntity.ok("Grupo muscular eliminado");
    }
}
