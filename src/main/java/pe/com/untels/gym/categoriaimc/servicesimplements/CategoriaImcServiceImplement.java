package pe.com.untels.gym.categoriaimc.servicesimplements;

import pe.com.untels.gym.categoriaimc.entities.CategoriaImc;
import pe.com.untels.gym.categoriaimc.repositories.ICategoriaIMCRepository;
import pe.com.untels.gym.categoriaimc.servicesinterfaces.ICategoriaImcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaImcServiceImplement implements ICategoriaImcService {
    @Autowired
    private ICategoriaIMCRepository provR;

    @Override
    public List<CategoriaImc> list() {
        return provR.findAll();
    }

}
