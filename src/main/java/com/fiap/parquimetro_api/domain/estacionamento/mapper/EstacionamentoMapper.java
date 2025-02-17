package com.fiap.parquimetro_api.domain.estacionamento.mapper;

import com.fiap.parquimetro_api.domain.condutor.mapper.CondutorMapper;
import com.fiap.parquimetro_api.domain.estacionamento.entity.Estacionamento;
import com.fiap.parquimetro_api.domain.estacionamento.dto.EstacionamentoDTO;
import com.fiap.parquimetro_api.domain.veiculo.mapper.VeiculoMapper;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;


public class EstacionamentoMapper {

    public static List<EstacionamentoDTO> toListDTO(List<Estacionamento> estacionamentos) {
        if(ObjectUtils.isEmpty(estacionamentos)) {
            return null;
        }
        List<EstacionamentoDTO> dtoList = new ArrayList<>();
        estacionamentos.forEach(estacionamento -> dtoList.add(new EstacionamentoDTO(estacionamento)));
        return dtoList;
    }
    public static Estacionamento toEstacionamento(EstacionamentoDTO estacionamentoDTO) {
        if(ObjectUtils.isEmpty(estacionamentoDTO)) {
            return null;
        }
        Estacionamento estacionamento = new Estacionamento();
        estacionamento.setIdEstacionamento(estacionamentoDTO.getIdEstacionamento());
        estacionamento.setInicio(estacionamentoDTO.getInicio());
        estacionamento.setFim(estacionamentoDTO.getFim());
        estacionamento.setCondutor(CondutorMapper.toEntity(estacionamentoDTO.getCondutorDTO()));
        estacionamento.setVeiculo(VeiculoMapper.toEntity(estacionamentoDTO.getVeiculoDTO()));
        return estacionamento;
    }

    public static EstacionamentoDTO toDTO(Estacionamento estacionamento) {
        if(ObjectUtils.isEmpty(estacionamento)) {
            return null;
        }
        EstacionamentoDTO estacionamentoDTO = new EstacionamentoDTO();
        estacionamento.setIdEstacionamento(estacionamento.getIdEstacionamento());
        estacionamento.setInicio(estacionamento.getInicio());
        estacionamento.setFim(estacionamento.getFim());
        estacionamento.setCondutor(estacionamento.getCondutor());
        estacionamento.setVeiculo(estacionamento.getVeiculo());
        return estacionamentoDTO;

    }

    public static Estacionamento toEntity(EstacionamentoDTO estacionamentoDTO) {
        if(ObjectUtils.isEmpty(estacionamentoDTO)) {
            return null;
        }
        Estacionamento estacionamento = new Estacionamento();
        estacionamento.setIdEstacionamento(estacionamentoDTO.getIdEstacionamento());
        estacionamento.setInicio(estacionamentoDTO.getInicio());
        estacionamento.setFim(estacionamentoDTO.getFim());
        estacionamento.setCondutor(CondutorMapper.toEntity(estacionamentoDTO.getCondutorDTO()));
        estacionamento.setVeiculo(estacionamento.getVeiculo());
        return estacionamento;

    }
}
