package pe.com.untels.gym.seguridad.entities;

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
        ROLE_ADMIN,
        ROLE_USUARIO
    }

    @Enumerated(EnumType.STRING)
    private TipoRol privilegio = TipoRol.ROLE_USUARIO;

    @OneToMany(mappedBy = "rol")
    private List<Usuario> usuarios;

    public Rol(TipoRol privilegio) {
        this.privilegio = privilegio;
    }
}
