package pe.com.untels.gym.grupoMuscular.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.untels.gym.grupoMuscular.dtos.GrupoMuscularDTO;
import pe.com.untels.gym.grupoMuscular.dtos.GrupoMuscularInsertDTO;
import pe.com.untels.gym.grupoMuscular.entities.GrupoMuscular;
import pe.com.untels.gym.grupoMuscular.servicesinterfaces.IGrupoMuscularService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/grupoMuscular")
public class GrupoMuscularController {
    @Autowired
    private IGrupoMuscularService gS;

    @GetMapping("/lista")
    public ResponseEntity<List<GrupoMuscularDTO>> lista(){
        List<GrupoMuscularDTO> lista = gS.list().stream()
                .map(GrupoMuscularDTO::new)
                .toList();
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<GrupoMuscularInsertDTO> registrar(@RequestBody GrupoMuscularInsertDTO dto){
        GrupoMuscular gm = new GrupoMuscular();
        gm.setNombre(dto.getNombre());
        gm.setDescripcion(dto.getDescripcion());
        gm.setImagenGrupo(dto.getImagenGrupo());
        gm.setColorIndicador(dto.getColorIndicador());
        GrupoMuscular res = gS.insert(gm);
        GrupoMuscularInsertDTO responseDTO = new GrupoMuscularInsertDTO(gm);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        Optional<GrupoMuscular> gm = gS.listId(id);
        if (gm.isPresent()) {
            GrupoMuscularInsertDTO dto = new GrupoMuscularInsertDTO(gm.get());
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Grupo muscular no encontrado");
        }
    }
}