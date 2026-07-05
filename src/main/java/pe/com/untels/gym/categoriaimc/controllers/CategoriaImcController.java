package pe.com.untels.gym.categoriaimc.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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
@EnableMethodSecurity
public class CategoriaImcController {
    @Autowired
    private ICategoriaImcService categoriaImcService;

    @PreAuthorize("hasAnyRole('USUARIO', 'ADMIN')")
    @GetMapping("/lista")
    public ResponseEntity<List<CategoriaImcDTO>> lista(){
        ModelMapper modelMapper = new ModelMapper();
        List<CategoriaImc> categoriasImc = categoriaImcService.list();
        List<CategoriaImcDTO> listaCategoriaIMC = categoriasImc.stream()
                .map(categoriaImc -> modelMapper.map(categoriaImc, CategoriaImcDTO.class))
                .toList();
        return ResponseEntity.ok(listaCategoriaIMC);
    }

    @PostMapping("/nuevo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoriaImcInsertDTO> registrar(@RequestBody CategoriaImcInsertDTO request){
        ModelMapper modelMapper = new ModelMapper();
        CategoriaImc categoriaImc = modelMapper.map(request, CategoriaImc.class);
        CategoriaImcInsertDTO responseDTO = modelMapper.map(categoriaImcService.insert(categoriaImc), CategoriaImcInsertDTO.class);
        return  ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        Optional<CategoriaImc> categoriaimc = categoriaImcService.listId(id);
        if (categoriaimc.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Categoria no encontrada");
        }
        ModelMapper modelMapper = new ModelMapper();
        CategoriaImcInsertDTO response = modelMapper.map(categoriaimc, CategoriaImcInsertDTO.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        categoriaImcService.delete(id);
        return ResponseEntity.ok("Categoría eliminada");
    }
}
