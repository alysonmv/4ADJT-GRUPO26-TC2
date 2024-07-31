package com.fiap.parquimetro_api.domain.estacionamento.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.parquimetro_api.domain.condutor.dto.CondutorDTO;
import com.fiap.parquimetro_api.domain.estacionamento.entity.Estacionamento;
import com.fiap.parquimetro_api.domain.veiculo.dto.VeiculoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstacionamentoDTO {
    @JsonIgnore
    private Long idEstacionamento;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private VeiculoDTO veiculoDTO;
    private CondutorDTO condutorDTO;

    public EstacionamentoDTO(Estacionamento estacionamento) {
        this.idEstacionamento = estacionamento.getIdEstacionamento();
        this.inicio = estacionamento.getInicio();
        this.fim = estacionamento.getFim();
        this.veiculoDTO = new VeiculoDTO(estacionamento.getVeiculo());
        this.condutorDTO = new CondutorDTO(estacionamento.getCondutor());
    }
}
