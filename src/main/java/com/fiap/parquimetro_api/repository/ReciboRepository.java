package com.fiap.parquimetro_api.repository;

import com.fiap.parquimetro_api.domain.Recibo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReciboRepository extends JpaRepository<Recibo, Integer> {
}
