package pe.com.untels.gym.seguridad.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "roles")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRol;

    public enum TipoRol {
        ADMIN,
        USUARIO
    }

    @Enumerated(EnumType.STRING)
    private TipoRol privilegio = TipoRol.USUARIO;

    @OneToMany(mappedBy = "rol")
    private List<Usuario> usuarios;

    public Rol(TipoRol privilegio) {
        this.privilegio = privilegio;
    }
}
