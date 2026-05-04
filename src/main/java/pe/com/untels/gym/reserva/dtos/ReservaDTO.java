package pe.com.untels.gym.reserva.dtos;

import pe.com.untels.gym.reserva.modelo.Reserva;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaDTO {
    private int idUsuario;
    private LocalDate fechaReserva;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Reserva.EstadoReserva estado;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Reserva.EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(Reserva.EstadoReserva estado) {
        this.estado = estado;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }
}
