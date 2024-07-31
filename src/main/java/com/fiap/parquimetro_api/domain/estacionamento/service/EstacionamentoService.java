package com.fiap.parquimetro_api.domain.estacionamento.service;

import com.fiap.parquimetro_api.domain.condutor.entity.Condutor;
import com.fiap.parquimetro_api.domain.condutor.repository.CondutorRepository;
import com.fiap.parquimetro_api.domain.estacionamento.entity.Estacionamento;
import com.fiap.parquimetro_api.domain.estacionamento.dto.EstacionamentoDTO;
import com.fiap.parquimetro_api.domain.estacionamento.mapper.EstacionamentoMapper;
import com.fiap.parquimetro_api.domain.estacionamento.model.TipoDeEstacionamento;
import com.fiap.parquimetro_api.domain.estacionamento.repository.EstacionamentoRepository;
import com.fiap.parquimetro_api.domain.formadepagamento.model.FormaDePagamento;
import com.fiap.parquimetro_api.domain.veiculo.entity.Veiculo;
import com.fiap.parquimetro_api.domain.veiculo.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EstacionamentoService {
    @Autowired
    private EstacionamentoRepository estacionamentoRepository;

    @Autowired
    private CondutorRepository condutorRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    public ResponseEntity<EstacionamentoDTO> cadastrar (LocalDateTime dataEntrada, LocalDateTime dataSaida, String cpf, String placa, FormaDePagamento formaDePagamento, TipoDeEstacionamento tipoDeEstacionamento){

        Optional<Condutor> condutor = condutorRepository.findByCpf(cpf);

        if(ObjectUtils.isEmpty(condutor)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Veiculo veiculo = veiculoRepository.findByPlacaAndCondutor(placa, condutor.get());
        if(ObjectUtils.isEmpty(veiculo)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Estacionamento estacionamento = new Estacionamento();
        estacionamento.setCondutor(condutor.get());
        estacionamento.setVeiculo(veiculo);
        estacionamento.setFormaDePagamento(formaDePagamento);
        estacionamento.setInicio(dataEntrada);
        estacionamento.setFim(dataSaida);

        Estacionamento estacionamentoSalvo = estacionamentoRepository.save(estacionamento);

        return ResponseEntity.status(HttpStatus.OK).body(EstacionamentoMapper.toDTO(estacionamentoSalvo));
    }

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
