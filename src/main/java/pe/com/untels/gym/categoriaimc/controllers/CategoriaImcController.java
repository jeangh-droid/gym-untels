package pe.com.untels.gym.categoriaimc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.com.untels.gym.categoriaimc.dtos.CategoriaImcDTO;
import pe.com.untels.gym.categoriaimc.dtos.CategoriaImcInsertDTO;
import pe.com.untels.gym.categoriaimc.entities.CategoriaImc;
import pe.com.untels.gym.categoriaimc.servicesinterfaces.ICategoriaImcService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/CategoriaIMC")
public class CategoriaImcController {
    @Autowired
    private ICategoriaImcService pS;
    @GetMapping("/lista")
    public ResponseEntity<List<CategoriaImcDTO>> lista(){
        ModelMapper m= new ModelMapper();
        List<CategoriaImcDTO> listaCategoriaIMC=pS.list().stream().map(y->m.map(y,CategoriaImcDTO.class)).toList();

        return ResponseEntity.ok(listaCategoriaIMC);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<CategoriaImcInsertDTO> registrar(@RequestBody CategoriaImcInsertDTO dto){
        ModelMapper m=new ModelMapper();
        CategoriaImc c=m.map(dto, CategoriaImc.class);
        CategoriaImc prov= pS.insert(c);
        CategoriaImcInsertDTO responseDTO=m.map(prov,CategoriaImcInsertDTO.class);
        return  ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        Optional<CategoriaImc> categoriaimc = pS.listId(id);

        if (categoriaimc.isPresent()) {
            CategoriaImcInsertDTO dto = m.map(categoriaimc.get(), CategoriaImcInsertDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Categoria no encontrada");
        }
    }
}
