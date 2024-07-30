package com.fiap.parquimetro_api.domain.recibo.repository;

import com.fiap.parquimetro_api.domain.recibo.entity.Recibo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReciboRepository extends JpaRepository<Recibo, Integer> {
}
