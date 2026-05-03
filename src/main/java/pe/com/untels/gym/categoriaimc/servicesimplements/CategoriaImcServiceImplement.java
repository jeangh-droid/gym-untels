package pe.com.untels.gym.categoriaimc.servicesimplements;

import pe.com.untels.gym.categoriaimc.entities.CategoriaImc;
import pe.com.untels.gym.categoriaimc.repositories.ICategoriaIMCRepository;
import pe.com.untels.gym.categoriaimc.servicesinterfaces.ICategoriaImcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaImcServiceImplement implements ICategoriaImcService {
    @Autowired
    private ICategoriaIMCRepository provR;

    @Override
    public List<CategoriaImc> list() {
        return provR.findAll();
    }
    @Override
    public CategoriaImc insert(CategoriaImc cate) {
        return provR.save(cate);
    }
    @Override
    public Optional<CategoriaImc> listId(int id) {
        return provR.findById(id);
    }

}
