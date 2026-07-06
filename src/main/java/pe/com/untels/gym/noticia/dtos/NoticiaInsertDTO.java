package pe.com.untels.gym.noticia.dtos;

import lombok.Data;

@Data
public class NoticiaInsertDTO {
    private String titulo;
    private String contenido;
    private String tipo;
    private String imagenUrl;
}