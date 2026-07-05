package pe.com.untels.gym.noticia.dtos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class NoticiaDTO {
    private Integer idNoticia;
    private String titulo;
    private String contenido;
    private String tipo;
    private String imagenUrl;
    private LocalDate fechaPublicacion;
}