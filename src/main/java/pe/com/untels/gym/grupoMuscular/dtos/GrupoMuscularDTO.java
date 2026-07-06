package pe.com.untels.gym.grupoMuscular.dtos;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GrupoMuscularDTO {
    private Integer idGrupoMuscular; // FIX: sin esto no se puede editar/eliminar desde una lista
    private String nombre;
    private String colorIndicador;
}