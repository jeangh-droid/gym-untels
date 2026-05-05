package pe.com.untels.gym.reserva.dtos;

import lombok.Data;
import pe.com.untels.gym.reserva.modelo.Reserva;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservaInsertDTO {
    private Integer idUsuario;
    private LocalDate fechaReserva;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Reserva.EstadoReserva estado;
}
