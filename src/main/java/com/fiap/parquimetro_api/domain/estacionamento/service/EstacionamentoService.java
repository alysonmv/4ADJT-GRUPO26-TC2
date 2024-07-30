package com.fiap.parquimetro_api.domain.estacionamento.service;

import com.fiap.parquimetro_api.domain.estacionamento.entity.Estacionamento;
import com.fiap.parquimetro_api.domain.estacionamento.dto.EstacionamentoDTO;
import com.fiap.parquimetro_api.domain.estacionamento.mapper.EstacionamentoMapper;
import com.fiap.parquimetro_api.domain.estacionamento.repository.EstacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstacionamentoService {
    @Autowired
    private EstacionamentoRepository estacionamentoRepository;

    public List<EstacionamentoDTO> findAll() {
        List<Estacionamento> estacionamentos = estacionamentoRepository.findAll();
        return EstacionamentoMapper.toListDTO(estacionamentos);
    }

    public Optional<EstacionamentoDTO> findById(Long idEstacionamento) {
        return estacionamentoRepository.findById(idEstacionamento).map(EstacionamentoMapper::toDTO);
    }
    public EstacionamentoDTO save(EstacionamentoDTO estacionamentoDTO) {
        Estacionamento estacionamento = EstacionamentoMapper.toEntity(estacionamentoDTO);
        estacionamento = estacionamentoRepository.save(estacionamento);
        return EstacionamentoMapper.toDTO(estacionamento);
    }
    public void delete(Long idEstacionamento) {
        estacionamentoRepository.deleteById(idEstacionamento);
    }
}
