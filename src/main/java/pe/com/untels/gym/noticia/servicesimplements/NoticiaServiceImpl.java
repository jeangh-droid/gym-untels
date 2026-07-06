package pe.com.untels.gym.noticia.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.untels.gym.noticia.entities.Noticia;
import pe.com.untels.gym.noticia.repositories.INoticiaRepository;
import pe.com.untels.gym.noticia.servicesinterfaces.INoticiaService;

import java.util.List;

@Service
public class NoticiaServiceImpl implements INoticiaService {

    @Autowired
    private INoticiaRepository noticiaRepository;

    @Override
    public List<Noticia> list() {
        // Se podría ordenar por fecha de publicación descendente
        return noticiaRepository.findAll();
    }

    @Override
    public Noticia insert(Noticia noticia) {
        return noticiaRepository.save(noticia);
    }

    @Override
    public void delete(Integer id) {
        noticiaRepository.deleteById(id);
    }

    @Override
    public Noticia findById(Integer id) {
        return noticiaRepository.findById(id).orElseThrow(() -> new RuntimeException("Noticia no registrada"));
    }

    @Override
    public Noticia update(Noticia noticia) {
        return noticiaRepository.save(noticia);
    }
}