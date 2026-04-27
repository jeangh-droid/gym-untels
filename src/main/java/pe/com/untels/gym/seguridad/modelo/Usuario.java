package pe.com.untels.gym.seguridad.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(name = "codigoUniversitario", length = 50)
    private String codigoUniversitario;

    @Column(name = "nombreCompleto", nullable = false, length = 150)
    private String nombreCompleto;

    @Column(name = "correoInstitucional", nullable = false, unique = true, length = 120)
    private String correoInstitucional;

    @Column(name = "contraseña", nullable = false)
    private String contrasena;

    @Column(name = "genero", length = 20)
    private String genero;

    @Column(name = "fechaNacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "peso")
    private Double peso;

    @Column(name = "estatura")
    private Double estatura;

    @Column(name = "nivel")
    private Integer nivel;

    @Column(name = "objetivo", length = 200)
    private String objetivo;

    @Column(name = "diasEntrenamiento")
    private Integer diasEntrenamiento;

    @Column(name = "fechaRegistro", nullable = false, updatable = false)
    private LocalDate fechaRegistro;

    @JoinColumn(name = "rol_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Rol rol;

    @OneToMany(mappedBy = "usuario")
    private List<RefreshToken> refreshTokens;

    @PrePersist
    public void prePersist() {
        if (this.fechaRegistro == null) {
            this.fechaRegistro = LocalDate.now();
        }
    }
}
