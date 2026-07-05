package pe.com.untels.gym.progreso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.untels.gym.seguridad.entities.Usuario;
import java.time.LocalDate;

@Entity
@Table(name = "historial_progreso")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialProgreso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProgreso;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    @Column(nullable = false)
    private Double peso;

    private Double imc;

    @Column(name = "calorias_quemadas")
    private Integer caloriasQuemadas;

    @Column(name = "minutos_entrenados")
    private Integer minutosEntrenados;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
}