package com.fiap.parquimetro_api.domain.estacionamento.repository;

import com.fiap.parquimetro_api.domain.estacionamento.entity.Estacionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Long> {
}
