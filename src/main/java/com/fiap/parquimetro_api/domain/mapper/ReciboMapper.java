package com.fiap.parquimetro_api.domain.mapper;

import com.fiap.parquimetro_api.domain.Recibo;
import com.fiap.parquimetro_api.domain.dto.ReciboDTO;
import org.apache.commons.lang3.ObjectUtils;

public class ReciboMapper {
    public static ReciboDTO toDTO(Recibo recibo) {
        if(ObjectUtils.isEmpty(recibo)) {
            return null;
        }
        return new ReciboDTO(recibo);
    }
    public static Recibo toEntity(ReciboDTO reciboDTO) {
        if(ObjectUtils.isEmpty(reciboDTO)) {
            return null;
        }
        Recibo recibo = new Recibo();
        recibo.setIdRecibo(Math.toIntExact(reciboDTO.getIdRecibo()));
        recibo.setValorTotal(reciboDTO.getValorTotal());
        recibo.setEstacionamento(EstacionamentoMapper.toEntity(reciboDTO.getEstacionamentoDTO()));
        return recibo;
    }
}
