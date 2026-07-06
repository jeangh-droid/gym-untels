package pe.com.untels.gym.reserva.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import pe.com.untels.gym.reserva.dtos.ReservaDTO;
import pe.com.untels.gym.reserva.dtos.ReservaInsertDTO;
import pe.com.untels.gym.reserva.servicesinterfaces.IReservaService;
import pe.com.untels.gym.reserva.entities.Reserva;
import pe.com.untels.gym.seguridad.entities.Usuario;
import pe.com.untels.gym.usuario.services.UsuarioServicio;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
@EnableMethodSecurity
public class ReservaController {
    @Autowired
    private IReservaService reservaService;
    final UsuarioServicio usuarioServicio;

    // HUF06: Historial de reservas
    @GetMapping("/historial/{idUsuario}")
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMIN')")
    public ResponseEntity<List<ReservaDTO>> listarPorUsuario(@PathVariable int idUsuario) {
        ModelMapper modelMapper = new ModelMapper();
        List<ReservaDTO> lista = reservaService.historialReserva(idUsuario)
                .stream().map(reserva -> modelMapper.map(reserva, ReservaDTO.class))
                .toList();
        return ResponseEntity.ok(lista);
    }

    // HUF02: Reservar un horario
    @PostMapping("/nuevo")
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMIN')")
    public ResponseEntity<?> registrar(@RequestBody ReservaInsertDTO request) {
        Optional<Usuario> usuarioOptional = Optional.ofNullable(usuarioServicio.obtenerUsuario(request.getIdUsuario()));
        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no identificado");
        }
        ModelMapper modelMapper = new ModelMapper();
        Reserva reserva = modelMapper.map(request, Reserva.class);
        ReservaDTO reservaDTO = modelMapper.map(reservaService.insert(reserva), ReservaDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        Optional<Reserva> reserva = reservaService.listId(id);
        if (reserva.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Reserva no encontrada");
        }
        ModelMapper modelMapper = new ModelMapper();
        ReservaDTO reservaDTO = modelMapper.map(reserva, ReservaDTO.class);
        return ResponseEntity.ok(reservaDTO);
    }

    @PutMapping("/cancelar/{idReserva}")
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMIN')")
    public ResponseEntity<?> cancelarReserva(@PathVariable Integer idReserva) {
        reservaService.cambiarEstado(idReserva, Reserva.EstadoReserva.CANCELADO);
        return ResponseEntity.ok("Reserva cancelada exitosamente");
    }

    // NUEVO: Endpoint para el Dashboard de Agenda del Administrador
    @GetMapping("/lista-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ReservaDTO>> listarTodasLasReservas() {
        ModelMapper modelMapper = new ModelMapper();
        List<ReservaDTO> lista = reservaService.list().stream()
                .map(reserva -> {
                    ReservaDTO dto = modelMapper.map(reserva, ReservaDTO.class);
                    return dto;
                })
                .toList();

        return ResponseEntity.ok(lista);
    }
}
