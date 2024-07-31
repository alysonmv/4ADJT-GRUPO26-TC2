package com.fiap.parquimetro_api.domain.estacionamento.service;


import com.fiap.parquimetro_api.domain.condutor.entity.Condutor;
import com.fiap.parquimetro_api.domain.condutor.entity.TipoPagamento;
import com.fiap.parquimetro_api.domain.condutor.repository.CondutorRepository;
import com.fiap.parquimetro_api.domain.estacionamento.entity.Estacionamento;
import com.fiap.parquimetro_api.domain.estacionamento.dto.EstacionamentoDTO;
import com.fiap.parquimetro_api.domain.estacionamento.mapper.EstacionamentoMapper;
import com.fiap.parquimetro_api.domain.estacionamento.repository.EstacionamentoRepository;
import com.fiap.parquimetro_api.domain.recibo.dto.ReciboDTO;
import com.fiap.parquimetro_api.domain.recibo.entity.Recibo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EstacionamentoService {
    @Autowired
    private EstacionamentoRepository estacionamentoRepository;
    @Autowired
    private CondutorRepository condutorRepository;

    public List<EstacionamentoDTO> findAll() {
        List<Estacionamento> estacionamentos = estacionamentoRepository.findAll();
        return EstacionamentoMapper.toListDTO(estacionamentos);
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

        Condutor condutor = this.condutorRepository
                .findById(idCondutor)
                .orElse(null);
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
