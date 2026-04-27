package pe.com.untels.gym.seguridad.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "refresh_tokens")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRefreshToken;

    @Column(nullable = false)
    private String token;

    private boolean removido;

    private boolean expirado;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
