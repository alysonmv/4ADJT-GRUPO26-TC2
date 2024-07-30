package com.fiap.parquimetro_api.domain.veiculo.repository;

import com.fiap.parquimetro_api.domain.veiculo.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository <Veiculo, Long> {

    Veiculo findByPlaca(String placa);

}
