package com.fiap.parquimetro_api.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.parquimetro_api.domain.Recibo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReciboDTO {
    @JsonIgnore
    private Long idRecibo;
    private BigDecimal valorTotal;
    private EstacionamentoDTO estacionamentoDTO;

    public ReciboDTO(Recibo recibo) {
        this.idRecibo = (long) recibo.getIdRecibo();
        this.valorTotal = recibo.getValorTotal();
        this.estacionamentoDTO = new EstacionamentoDTO(recibo.getEstacionamento());
    }
}
