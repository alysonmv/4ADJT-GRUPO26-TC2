package com.fiap.parquimetro_api.domain.estacionamento.dto;

import com.fiap.parquimetro_api.domain.condutor.entity.TipoPagamento;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
public class EstacionamentoSaidaDTO {
    @NotNull(message ="id deve ser preenchido")
    private String id;
    private LocalDateTime saida;
    @NotNull(message = "Informacao de forma de pagamento  é obrigatória")
    private TipoPagamento tipoPagamento;

}