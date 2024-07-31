package com.fiap.parquimetro_api.domain.estacionamento.dto;

import com.fiap.parquimetro_api.domain.estacionamento.entity.PeriodoEstacionamento;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EstacionamentoEntradaDTO {

    @FutureOrPresent(message="Data não poder menor que agora!")
    private LocalDateTime entrada;
    @FutureOrPresent(message="Data não poder menor que agora!")
    private LocalDateTime saida;
    private PeriodoEstacionamento periodoEstacionamento;
    private String condutorCPF;
    private String veiculoPlaca;


}