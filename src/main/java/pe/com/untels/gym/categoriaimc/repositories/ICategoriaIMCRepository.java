package pe.com.untels.gym.categoriaimc.repositories;

import pe.com.untels.gym.categoriaimc.entities.CategoriaImc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaIMCRepository extends JpaRepository <CategoriaImc, Integer>{
}
