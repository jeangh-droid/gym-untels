package pe.com.untels.gym.categoriaimc.servicesinterfaces;

import pe.com.untels.gym.categoriaimc.entities.CategoriaImc;

import java.util.List;
import java.util.Optional;

public interface ICategoriaImcService {
    public List<CategoriaImc>list();
    public CategoriaImc insert(CategoriaImc cate);
    public Optional<CategoriaImc> listId(int id);
}

