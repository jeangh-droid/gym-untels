package pe.com.untels.gym.noticia.servicesinterfaces;

import pe.com.untels.gym.noticia.entities.Noticia;
import java.util.List;

public interface INoticiaService {
    List<Noticia> list();
    Noticia insert(Noticia noticia);
    void delete(Integer id);
    Noticia findById(Integer id);
    Noticia update(Noticia noticia);
}