package pe.com.untels.gym.categoriaimc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.com.untels.gym.categoriaimc.dtos.CategoriaImcDTO;
import pe.com.untels.gym.categoriaimc.dtos.CategoriaImcInsertDTO;
import pe.com.untels.gym.categoriaimc.entities.CategoriaImc;
import pe.com.untels.gym.categoriaimc.servicesinterfaces.ICategoriaImcService;
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
        List<CategoriaImcDTO> listaCategoriaIMC = pS.list().stream()
                .map(CategoriaImcDTO::new)
                .toList();
        return ResponseEntity.ok(listaCategoriaIMC);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<CategoriaImcInsertDTO> registrar(@RequestBody CategoriaImcInsertDTO dto){
        CategoriaImc c = new CategoriaImc();
        c.setNombre(dto.getNombre());
        c.setRangoMax(dto.getRangoMax());
        c.setRangoMin(dto.getRangoMin());
        CategoriaImcInsertDTO responseDTO = new CategoriaImcInsertDTO(pS.insert(c));
        return  ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        Optional<CategoriaImc> categoriaimc = pS.listId(id);

        if (categoriaimc.isPresent()) {
            CategoriaImcInsertDTO dto = new CategoriaImcInsertDTO(categoriaimc.get());
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Categoria no encontrada");
        }
    }
}
