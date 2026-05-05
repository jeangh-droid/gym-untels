package pe.com.untels.gym.reserva.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.untels.gym.seguridad.entidad.Usuario;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReserva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @Column(name = "fechaReserva", nullable = false)
    private LocalDate fechaReserva;

    @Column(name = "horaInicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "horaFin", nullable = false)
    private LocalTime horaFin;

    public enum EstadoReserva {
        PENDIENTE,
        ASISTIDO,
        CANCELADO,
        NO_ASISTIO
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoReserva estado;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDate fechaCreacion;

    @PrePersist
    private void auto() {
        if (this.fechaCreacion == null) {
            this.fechaCreacion = LocalDate.now();
        }
        if (this.estado == null) {
            this.estado = EstadoReserva.PENDIENTE;
        }
    }
}
