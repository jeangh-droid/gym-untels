package pe.com.untels.gym.categoriaimc.controllers;

import pe.com.untels.gym.categoriaimc.dtos.CategoriaImcDTO;
import pe.com.untels.gym.categoriaimc.servicesinterfaces.ICategoriaImcService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
}
