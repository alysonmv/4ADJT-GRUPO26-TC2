package com.fiap.parquimetro_api.repository;

import com.fiap.parquimetro_api.domain.FormaDePagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaDePagamentoRepository extends JpaRepository<FormaDePagamento, Long> {
}
