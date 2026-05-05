package pe.com.untels.gym.grupoMuscular.dtos;

import lombok.Data;
import pe.com.untels.gym.grupoMuscular.entities.GrupoMuscular;

@Data
public class GrupoMuscularDTO {
    private String nombre;
    private String colorIndicador;

    public GrupoMuscularDTO(GrupoMuscular grupoMuscular) {
        this.nombre = grupoMuscular.getNombre();
        this.colorIndicador = grupoMuscular.getColorIndicador();
    }
}