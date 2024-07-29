package com.fiap.parquimetro_api.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.parquimetro_api.domain.Estacionamento;
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
    private FormaDePagamentoDTO formaDePagamentoDTO;
    private CondutorDTO condutorDTO;

    public EstacionamentoDTO(Estacionamento estacionamento) {
        this.idEstacionamento = estacionamento.getIdEstacionamento();
        this.inicio = estacionamento.getInicio();
        this.fim = estacionamento.getFim();
        this.veiculoDTO = new VeiculoDTO(estacionamento.getVeiculo());
        this.formaDePagamentoDTO = new FormaDePagamentoDTO(estacionamento.getFormaDePagamento());
        this.condutorDTO = new CondutorDTO(estacionamento.getCondutor());
    }
}
