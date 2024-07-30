package com.fiap.parquimetro_api.domain.condutor.repository;

import com.fiap.parquimetro_api.domain.condutor.entity.Condutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CondutorRepository extends JpaRepository <Condutor, Long> {

    Condutor findByCpf(String cpf);

}
