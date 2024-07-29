package com.fiap.parquimetro_api.service;

import com.fiap.parquimetro_api.domain.FormaDePagamento;
import com.fiap.parquimetro_api.domain.dto.FormaDePagamentoDTO;
import com.fiap.parquimetro_api.domain.mapper.FormaDePagamentoMapper;
import com.fiap.parquimetro_api.repository.FormaDePagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormaDePagamentoService {

    @Autowired
    private FormaDePagamentoRepository formaDePagamentoRepository;

    public List<FormaDePagamentoDTO> findAll() {
        List<FormaDePagamento> formaDePagamento = formaDePagamentoRepository.findAll();
        return formaDePagamento.stream().map(FormaDePagamentoMapper::toDTO).toList();
    }

    public Optional<FormaDePagamentoDTO> findById(Long formaDePagamentoId) {
        return formaDePagamentoRepository.findById(formaDePagamentoId)
                .map(FormaDePagamentoMapper::toDTO);
    }
    public FormaDePagamentoDTO save(FormaDePagamentoDTO formaDePagamentoDTO) {
        FormaDePagamento formaDePagamento = FormaDePagamentoMapper.toEntity(formaDePagamentoDTO);
        formaDePagamento = formaDePagamentoRepository.save(formaDePagamento);
        return FormaDePagamentoMapper.toDTO(formaDePagamento);
    }

    public void delete(Long formaDePagamentoId) {
        formaDePagamentoRepository.deleteById(formaDePagamentoId);
    }

}
