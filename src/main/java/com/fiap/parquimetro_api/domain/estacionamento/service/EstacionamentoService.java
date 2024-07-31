package com.fiap.parquimetro_api.domain.estacionamento.service;


import com.fiap.parquimetro_api.domain.condutor.entity.Condutor;
import com.fiap.parquimetro_api.domain.condutor.entity.TipoPagamento;
import com.fiap.parquimetro_api.domain.condutor.repository.CondutorRepository;
import com.fiap.parquimetro_api.domain.estacionamento.dto.EstacionamentoSaidaDTO;
import com.fiap.parquimetro_api.domain.estacionamento.entity.Estacionamento;
import com.fiap.parquimetro_api.domain.estacionamento.dto.EstacionamentoDTO;
import com.fiap.parquimetro_api.domain.estacionamento.entity.PeriodoEstacionamento;
import com.fiap.parquimetro_api.domain.estacionamento.mapper.EstacionamentoMapper;
import com.fiap.parquimetro_api.domain.estacionamento.model.TipoDeEstacionamento;
import com.fiap.parquimetro_api.domain.estacionamento.repository.EstacionamentoRepository;
import com.fiap.parquimetro_api.domain.recibo.dto.ReciboDTO;
import com.fiap.parquimetro_api.domain.recibo.entity.Recibo;
import com.fiap.parquimetro_api.domain.veiculo.entity.Veiculo;
import com.fiap.parquimetro_api.domain.veiculo.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
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
    public ResponseEntity<?> encerrarEstacionamento(EstacionamentoSaidaDTO estacionamentoSaidaDTO) {

        Optional<Estacionamento> estacionamento = this.estacionamentoRepository.findById(Long.valueOf(estacionamentoSaidaDTO.getId()));

        if(estacionamento == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi encontrado estacionamento");

        // caso seja periodo fixo, forçar a data informada na entrada
        if(estacionamento.getPeriodoEstacionamento() == PeriodoEstacionamento.PERIODO_FIXO)
            estacionamentoSaidaDTO.setSaida(estacionamento.getDtSaida());
        else
            estacionamentoSaidaDTO.setSaida(LocalDateTime.now());

        estacionamento.setFormaPagamento(estacionamentoSaidaDTO.getFormaPagamento());
        estacionamento.setDtSaida(estacionamentoSaidaDTO.getSaida());
        calcularRecibo(estacionamento);

        try{
            return ResponseEntity.status(HttpStatus.OK).body(estacionamentoRepository.save(estacionamento));
        }catch(Exception ex){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }

    }

    public Optional<EstacionamentoDTO> findById(Long idEstacionamento) {
        return estacionamentoRepository.findById(idEstacionamento).map(EstacionamentoMapper::toDTO);
    }
    public EstacionamentoDTO save(EstacionamentoDTO estacionamentoDTO) {
        Condutor condutor = condutorRepository.findById(estacionamentoDTO.getCondutorDTO().getIdCondutor())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Condutor não encontrado"));
        if(condutor.getTipoPagamento() == TipoPagamento.PIX && estacionamentoDTO.getFim() == null) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "A opção PIX só é valida para período de estacionamento fixo");
        }

        Estacionamento estacionamento = EstacionamentoMapper.toEntity(estacionamentoDTO);
        estacionamento = estacionamentoRepository.save(estacionamento);
        return EstacionamentoMapper.toDTO(estacionamento);
    }
    public void delete(Long idEstacionamento) {
        estacionamentoRepository.deleteById(idEstacionamento);
    }

    public ResponseEntity<?> gerarRecibo(Long idEstacionamento) {
        Estacionamento estacionamento = this.estacionamentoRepository
                .findById(idEstacionamento)
                .orElse(null);
        if(estacionamento == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("estacionamento não encontrado");
        }
        ReciboDTO recibo = new ReciboDTO();
        String nome = estacionamento.getCondutor().getNome();
        String cpf = estacionamento.getCondutor().getCpf();
        BigDecimal valor = recibo.getValorTotal();
        LocalDateTime data = estacionamento.getFim();
        String forma;

        Condutor condutor = (Condutor) condutorRepository;
        if(condutor.getTipoPagamento() == TipoPagamento.CREDITO)
            forma = "Cartão de crédito";
        else if (condutor.getTipoPagamento() == TipoPagamento.DEBITO) {
            forma = "Debito";
        } else
            forma = "PIX";
        recibo.setMensagem("Recebemos do Sr(a): " +nome+ "CPF: " +cpf+ "o valor de R$ "+valor+ "na data de: " +data+ "na forma de " +forma);

        return ResponseEntity.status(HttpStatus.OK).body(recibo.getMensagem());
    }

    public void calcularRecibo(Estacionamento estacionamento, Recibo recibo) {
        long minutos =
                Duration.between(estacionamento.getInicio(), estacionamento.getFim()).toMinutes();
        double horas = minutos / 60.0;
        int horasTotal=(int) Math.ceil(horas);
        recibo.setValorTotal(BigDecimal.valueOf(horasTotal *3));
    }
}
