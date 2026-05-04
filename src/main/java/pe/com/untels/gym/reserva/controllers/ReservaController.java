package pe.com.untels.gym.reserva.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.untels.gym.reserva.dtos.ReservaDTO;
import pe.com.untels.gym.reserva.dtos.ReservaInsertDTO;
import pe.com.untels.gym.reserva.servicesinterfaces.IReservaService;
import pe.com.untels.gym.reserva.modelo.Reserva;
import pe.com.untels.gym.seguridad.modelo.Usuario;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {
    @Autowired
    private IReservaService rS;

    @GetMapping("/lista")
    public ResponseEntity<List<ReservaDTO>> listar() {
        ModelMapper m = new ModelMapper();

        List<ReservaDTO> lista = rS.list()
                .stream()
                .map(r -> {
                    ReservaDTO dto = m.map(r, ReservaDTO.class);
                    dto.setIdUsuario(r.getUsuario().getIdUsuario());
                    return dto;
                })
                .toList();

        return ResponseEntity.ok(lista);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<ReservaInsertDTO> registrar(@RequestBody ReservaInsertDTO dto) {
        ModelMapper m = new ModelMapper();

        Reserva r = m.map(dto, Reserva.class);


        Usuario u = new Usuario();
        u.setIdUsuario(dto.getIdUsuario());
        r.setUsuario(u);

        Reserva saved = rS.insert(r);

        ReservaInsertDTO response = m.map(saved, ReservaInsertDTO.class);
        response.setIdUsuario(saved.getUsuario().getIdUsuario());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        ModelMapper m = new ModelMapper();

        Optional<Reserva> reserva = rS.listId(id);

        if (reserva.isPresent()) {
            Reserva r = reserva.get();
            ReservaInsertDTO dto = m.map(r, ReservaInsertDTO.class);
            dto.setIdUsuario(r.getUsuario().getIdUsuario());

            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Reserva no encontrada");
        }
    }
}
