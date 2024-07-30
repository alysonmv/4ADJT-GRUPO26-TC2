package com.fiap.parquimetro_api.domain.recibo.service;

import com.fiap.parquimetro_api.domain.recibo.entity.Recibo;
import com.fiap.parquimetro_api.domain.recibo.dto.ReciboDTO;
import com.fiap.parquimetro_api.domain.recibo.mapper.ReciboMapper;
import com.fiap.parquimetro_api.domain.recibo.repository.ReciboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReciboService {
    @Autowired
    private ReciboRepository reciboRepository;

    public List<ReciboDTO> findALL() {
        List<Recibo> recibos = reciboRepository.findAll();
        return recibos.stream().map(ReciboMapper::toDTO).toList();
    }
    public Optional<ReciboDTO> findById(Long idRecibo) {
        return reciboRepository.findById(Math.toIntExact(idRecibo)).map(ReciboMapper::toDTO);
    }
    public ReciboDTO save(ReciboDTO reciboDTO) {
        Recibo recibo = ReciboMapper.toEntity(reciboDTO);
        recibo = reciboRepository.save(recibo);
        return ReciboMapper.toDTO(recibo);
    }
    public void delete(Long idRecibo) {
        reciboRepository.deleteById(Math.toIntExact(idRecibo));
    }
}
