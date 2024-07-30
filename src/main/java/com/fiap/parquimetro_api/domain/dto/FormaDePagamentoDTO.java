package com.fiap.parquimetro_api.domain.dto;

import com.fiap.parquimetro_api.domain.FormaDePagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormaDePagamentoDTO {
    private Long idFormaDePagamento;
    private String tipoPagamento;

    public FormaDePagamentoDTO(FormaDePagamento formaDePagamento) {
        this.idFormaDePagamento = formaDePagamento.getIdFormaDePagamento();
        this.tipoPagamento = formaDePagamento.getTipoPagamento();
    }
}
