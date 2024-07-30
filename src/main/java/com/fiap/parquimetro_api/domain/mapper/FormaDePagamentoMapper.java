package com.fiap.parquimetro_api.domain.mapper;

import com.fiap.parquimetro_api.domain.FormaDePagamento;
import com.fiap.parquimetro_api.domain.dto.FormaDePagamentoDTO;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class FormaDePagamentoMapper {
    public static FormaDePagamentoDTO toDTO(FormaDePagamento formaDePagamento) {
        if(ObjectUtils.isEmpty(formaDePagamento)) {
            return null;
        }
        return new FormaDePagamentoDTO(formaDePagamento);
    }
    public static FormaDePagamento toEntity(FormaDePagamentoDTO formaDePagamentoDTO) {
        if(ObjectUtils.isEmpty(formaDePagamentoDTO)) {
            return null;
        }
        FormaDePagamento formaDePagamento = new FormaDePagamento();
        formaDePagamento.setIdFormaDePagamento(formaDePagamentoDTO.getIdFormaDePagamento());
        formaDePagamento.setTipoPagamento(formaDePagamentoDTO.getTipoPagamento());
        return formaDePagamento;
    }
    public static List<FormaDePagamentoDTO> toListDTO(List<FormaDePagamento> formaDePagamentoList) {
        if(ObjectUtils.isEmpty(formaDePagamentoList)) {
            return null;
        }
        List<FormaDePagamentoDTO> listDTO = new ArrayList<>();
        formaDePagamentoList.forEach(formaDePagamento -> {
            FormaDePagamentoDTO formaDePagamentoDTO = new FormaDePagamentoDTO();
            formaDePagamentoDTO.setIdFormaDePagamento(formaDePagamento.getIdFormaDePagamento());
            formaDePagamentoDTO.setTipoPagamento(formaDePagamento.getTipoPagamento());
            listDTO.add(formaDePagamentoDTO);
        });
        return listDTO;
    }

}
