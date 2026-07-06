package pe.com.untels.gym.rutina.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import pe.com.untels.gym.rutina.dto.RutinaDTO;
import pe.com.untels.gym.rutina.dto.RutinaInsertDTO;
import pe.com.untels.gym.rutina.entities.Rutina;
import pe.com.untels.gym.rutina.servicesinterfaces.IRutinaService;

import java.util.List;

@RestController
@RequestMapping("/api/rutina")
@EnableMethodSecurity
public class RutinaController {
    @Autowired
    private IRutinaService rutinaService;
    @GetMapping("/lista")
    public ResponseEntity<List<RutinaDTO>>listar(){
        ModelMapper modelMapper = new ModelMapper();
        List<RutinaDTO> listaRutinaDTO= rutinaService.list().stream()
                .map(rutina -> modelMapper.map(rutina, RutinaDTO.class))
                .toList();
        return ResponseEntity.ok(listaRutinaDTO);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<RutinaDTO> registrar(@RequestBody RutinaInsertDTO request){
        ModelMapper modelMapper = new ModelMapper();
        Rutina rutina = modelMapper.map(request, Rutina.class);
        RutinaDTO responseDTO = modelMapper
                .map(rutinaService.insert(rutina), RutinaDTO.class);
        return  ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        rutinaService.delete(id);
        return ResponseEntity.ok("Rutina eliminada");
    }
}
