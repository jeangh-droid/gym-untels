package pe.com.untels.gym.noticia.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "noticias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNoticia;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String contenido;

    @Column(length = 50)
    private String tipo; // ej: "DESTACADA", "EVENTO", "AVISO"

    @Column(name = "imagen_url", length = 255)
    private String imagenUrl;

    @Column(name = "fecha_publicacion", nullable = false)
    private LocalDate fechaPublicacion;
}