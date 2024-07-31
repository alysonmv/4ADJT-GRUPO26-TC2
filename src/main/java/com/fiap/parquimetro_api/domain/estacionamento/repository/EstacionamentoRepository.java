package com.fiap.parquimetro_api.domain.estacionamento.repository;

import com.fiap.parquimetro_api.domain.estacionamento.entity.Estacionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Long> {
    public List<Estacionamento> findEstacionamentosByValorIsNullAndFormaPagamentoIsNull();
}
