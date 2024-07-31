package com.fiap.parquimetro_api.domain.condutor.repository;

import com.fiap.parquimetro_api.domain.condutor.entity.Condutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CondutorRepository extends JpaRepository <Condutor, Long> {

    Optional<Condutor> findByCpf(String cpf);

}
