package pe.com.untels.gym.rutina.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.untels.gym.rutina.dto.RutinaDTO;
import pe.com.untels.gym.rutina.dto.RutinaInsertDTO;
import pe.com.untels.gym.rutina.entities.Rutina;
import pe.com.untels.gym.rutina.servicesinterfaces.IRutinaService;

import java.util.List;

@RestController
@RequestMapping("/api/rutina")
public class RutinaController {
    @Autowired
    private IRutinaService rS;
    @GetMapping("/lista")
    public ResponseEntity<List<RutinaDTO>>listar(){
        List<Rutina> listaRutina = rS.list();
        List<RutinaDTO> listaRutinaDTO= listaRutina.stream()
                .map(RutinaDTO::new)
                .toList();
        return ResponseEntity.ok(listaRutinaDTO);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<RutinaDTO> registrar(@RequestBody RutinaInsertDTO dto){
        Rutina rutina = new Rutina();
        rutina.setNombre(dto.getNombre());
        rutina.setDescripcion(dto.getDescripcion());
        rutina.setFechaCreacion(dto.getFechaCreacion());
        Rutina cur= rS.insert(rutina);
        RutinaDTO responseDTO = new RutinaDTO(cur);
        return  ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

}
