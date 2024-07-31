package com.fiap.parquimetro_api.domain.estacionamento.mensageria;

import com.fiap.parquimetro_api.domain.estacionamento.entity.Estacionamento;
import com.fiap.parquimetro_api.domain.estacionamento.entity.PeriodoEstacionamento;
import com.fiap.parquimetro_api.domain.estacionamento.repository.EstacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class Monitoramento {
    private EstacionamentoRepository estacionamentoRepository;

    @Autowired
    public Monitoramento(EstacionamentoRepository estacionamentoRepository) {
        this.estacionamentoRepository = estacionamentoRepository;
    }
    @Scheduled(cron = "0 0/5 * * * ?")
    public void mensagemExpiracao() {
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        List<Estacionamento> listaEstacionamento = estacionamentoRepository.findEstacionamentosByValorIsNullAndFormaPagamentoIsNull();
        System.out.println(listaEstacionamento != null? "Existem " + listaEstacionamento.size() + " estacionamentos em andamento": "Nenhum estacionamento em andamento");
        for (Estacionamento vigente : listaEstacionamento) {
            if (vigente.getPeriodoEstacionamento().equals(PeriodoEstacionamento.PERIODO_FIXO)) {
                if (vigente.getDtSaida().isAfter(dataHoraAtual)) {
                    Long diferenca = ChronoUnit.MINUTES.between(vigente.getDtSaida(), dataHoraAtual);
                    if (diferenca >= -10) {
                        System.out.println("Estacionamendo(" + vigente.getIdEstacionamento() + ") que faltam menos de 10 minutos para encerrar seu tempo de estacionamento!");
                    }
                } else {
                    System.out.println("Atenção o Estacionamendo(" + vigente.getIdEstacionamento() +")! está com o Tempo de estacionamento esgotado!");
                }
            } else {
                Long diferenca = ChronoUnit.MINUTES.between(vigente.getDtEntrada(), dataHoraAtual);
                Double horas = diferenca / 60.0d;
                int diferencaHoras = (int) Math.ceil(horas);
                LocalDateTime horasPassadas = vigente.getDtEntrada().plusHours((long) diferencaHoras);
                diferenca = ChronoUnit.MINUTES.between(horasPassadas, dataHoraAtual);
                if (diferenca >= -10) {
                    System.out.println("Estacionamendo("+ vigente.getIdEstacionamento() +") que faltam menos de 10 minutos para completar" + diferencaHoras + " de estacionamento!");
                }
            }
        }
    }
}
