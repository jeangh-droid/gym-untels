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
}
