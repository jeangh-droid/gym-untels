package pe.com.untels.gym.usuario.dtos;

import lombok.Data;

@Data
public class UsuarioActualizarDTO {
    private Double peso;
    private Double estatura;
    private Integer nivel;
    private String objetivo;
}