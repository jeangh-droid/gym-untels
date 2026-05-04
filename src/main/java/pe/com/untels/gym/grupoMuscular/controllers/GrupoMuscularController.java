package pe.com.untels.gym.grupoMuscular.controllers;

import org.modelmapper.ModelMapper;
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
@RequestMapping("/api/GrupoMuscular")
public class GrupoMuscularController {
    @Autowired
    private IGrupoMuscularService gS;

    @GetMapping("/lista")
    public ResponseEntity<List<GrupoMuscularDTO>> lista(){
        ModelMapper m = new ModelMapper();
        List<GrupoMuscularDTO> lista = gS.list().stream()
                .map(y -> m.map(y, GrupoMuscularDTO.class))
                .toList();
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<GrupoMuscularInsertDTO> registrar(@RequestBody GrupoMuscularInsertDTO dto){
        ModelMapper m = new ModelMapper();
        GrupoMuscular gm = m.map(dto, GrupoMuscular.class);
        GrupoMuscular res = gS.insert(gm);
        GrupoMuscularInsertDTO responseDTO = m.map(res, GrupoMuscularInsertDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        Optional<GrupoMuscular> gm = gS.listId(id);
        if (gm.isPresent()) {
            GrupoMuscularInsertDTO dto = m.map(gm.get(), GrupoMuscularInsertDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Grupo muscular no encontrado");
        }
    }
}