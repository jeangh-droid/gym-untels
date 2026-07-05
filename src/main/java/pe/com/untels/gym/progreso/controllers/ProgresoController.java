package pe.com.untels.gym.progreso.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.com.untels.gym.progreso.dtos.ProgresoDTO;
import pe.com.untels.gym.progreso.dtos.ProgresoInsertDTO;
import pe.com.untels.gym.progreso.entities.HistorialProgreso;
import pe.com.untels.gym.progreso.servicesinterfaces.IProgresoService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/progreso")
public class ProgresoController {

    @Autowired
    private IProgresoService progresoService;

    // AMBOS PUEDEN VER: El usuario ve su gráfico, el admin puede revisar el de cualquier usuario
    @GetMapping("/historial/{idUsuario}")
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMIN')")
    public ResponseEntity<List<ProgresoDTO>> obtenerHistorial(@PathVariable Integer idUsuario) {
        ModelMapper modelMapper = new ModelMapper();
        List<ProgresoDTO> lista = progresoService.obtenerPorUsuario(idUsuario).stream()
                .map(progreso -> modelMapper.map(progreso, ProgresoDTO.class))
                .toList();
        return ResponseEntity.ok(lista);
    }

    // AMBOS PUEDEN REGISTRAR: El usuario se pesa y lo registra. (Opcionalmente el admin también puede hacerlo por él).
    @PostMapping("/registrar")
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMIN')")
    public ResponseEntity<ProgresoDTO> registrar(@RequestBody ProgresoInsertDTO request) {
        ModelMapper modelMapper = new ModelMapper();
        HistorialProgreso progreso = modelMapper.map(request, HistorialProgreso.class);
        progreso.setFechaRegistro(LocalDate.now());

        ProgresoDTO response = modelMapper.map(progresoService.insert(progreso), ProgresoDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}