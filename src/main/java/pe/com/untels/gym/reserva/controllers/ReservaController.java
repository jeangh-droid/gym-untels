package pe.com.untels.gym.reserva.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.untels.gym.reserva.dtos.ReservaDTO;
import pe.com.untels.gym.reserva.dtos.ReservaInsertDTO;
import pe.com.untels.gym.reserva.servicesinterfaces.IReservaService;
import pe.com.untels.gym.reserva.modelo.Reserva;
import pe.com.untels.gym.seguridad.modelo.Usuario;
import pe.com.untels.gym.usuario.servicio.UsuarioServicio;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController {
    @Autowired
    private IReservaService rS;
    final UsuarioServicio usuarioServicio;

    // HUF06: Historial de reservas
    @GetMapping("/historial/{idUsuario}")
    public ResponseEntity<List<ReservaDTO>> listarPorUsuario(@PathVariable int idUsuario) {
        List<ReservaDTO> lista = rS.historialReserva(idUsuario)
                .stream()
                .map(r -> {
                    ReservaDTO dto = new ReservaDTO(r);
                    return dto;
                })
                .toList();
        return ResponseEntity.ok(lista);
    }

    // HUF02: Reservar un horario
    @PostMapping("/nuevo")
    public ResponseEntity<ReservaDTO> registrar(@RequestBody ReservaInsertDTO dto) {
        Usuario usuario = usuarioServicio.obtenerUsuario(dto.getIdUsuario());
        Reserva r = new Reserva();
        r.setUsuario(usuario);
        r.setFechaReserva(dto.getFechaReserva());
        r.setHoraInicio(dto.getHoraInicio());
        r.setHoraFin(dto.getHoraFin());
        r.setEstado(dto.getEstado());
        ReservaDTO reservaDTO = new ReservaDTO(r);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        Optional<Reserva> reserva = rS.listId(id);

        if (reserva.isPresent()) {
            Reserva r = reserva.get();
            ReservaDTO reservaDTO = new ReservaDTO(r);
            return ResponseEntity.ok(reservaDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Reserva no encontrada");
        }
    }
}
