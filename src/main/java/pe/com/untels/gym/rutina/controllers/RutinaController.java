package pe.com.untels.gym.rutina.controllers;

import org.modelmapper.ModelMapper;
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
        ModelMapper m=new ModelMapper();
        List<RutinaDTO> listaRutina= rS.list()
                .stream().map(y->m.map(y, RutinaDTO.class))
                .toList();

        return ResponseEntity.ok(listaRutina);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<RutinaInsertDTO> registrar(@RequestBody RutinaDTO dto){
        ModelMapper m=new ModelMapper();
        Rutina c=m.map(dto, Rutina.class);
        Rutina cur= rS.insert(c);
        RutinaInsertDTO responseDTO=m.map(cur,RutinaInsertDTO.class);
        return  ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

}
