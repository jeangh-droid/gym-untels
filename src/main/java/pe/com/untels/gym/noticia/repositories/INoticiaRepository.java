package pe.com.untels.gym.noticia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.untels.gym.noticia.entities.Noticia;

@Repository
public interface INoticiaRepository extends JpaRepository<Noticia, Integer> {
}