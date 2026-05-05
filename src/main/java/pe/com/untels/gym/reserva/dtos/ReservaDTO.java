package pe.com.untels.gym.reserva.dtos;

import lombok.Data;
import pe.com.untels.gym.reserva.entities.Reserva;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservaDTO {
    private Integer idReserva;
    private LocalDate fechaReserva;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Reserva.EstadoReserva estado;

    public ReservaDTO(Reserva reserva) {
        this.idReserva = reserva.getIdReserva();
        this.fechaReserva = reserva.getFechaReserva();
        this.horaInicio = reserva.getHoraInicio();
        this.horaFin = reserva.getHoraFin();
        this.estado = reserva.getEstado();
    }
}
