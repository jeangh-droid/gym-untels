package pe.com.untels.gym.categoriaimc.servicesinterfaces;

import java.util.List;
import java.util.Optional;
import pe.com.untels.gym.categoriaimc.entities.CategoriaImc;

public interface ICategoriaImcService {
    List<CategoriaImc> list();

    CategoriaImc insert(CategoriaImc cate);

    Optional<CategoriaImc> listId(int id);

    void delete(int id);
}

