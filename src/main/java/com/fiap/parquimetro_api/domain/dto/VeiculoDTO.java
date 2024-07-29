package com.fiap.parquimetro_api.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.parquimetro_api.domain.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoDTO {

    @JsonIgnore
    private Long idVeiculo;
    private String placa;
    private String marca;
    private String modelo;

    public VeiculoDTO(Veiculo veiculo){
        this.idVeiculo = veiculo.getIdVeiculo();
        this.placa = veiculo.getPlaca();
        this.marca = veiculo.getMarca();
        this.modelo = veiculo.getModelo();
    }

}
