package pe.com.untels.gym.categoriaimc.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categorias_imc")
public class CategoriaImc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategoria;

    @Column(name = "nombre", length = 50, nullable = false, unique = true)
    private String nombre;

    @Column(name ="rangoMin",nullable = false)
    private int rangoMin;

    @Column(name = "rangoMax",nullable = false)
    private int rangoMax;
}
