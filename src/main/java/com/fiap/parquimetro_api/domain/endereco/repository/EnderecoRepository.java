package com.fiap.parquimetro_api.domain.endereco.repository;

import com.fiap.parquimetro_api.domain.endereco.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository <Endereco, Long> {

    Endereco findByNumeroAndCep(String numero, String cep);

}
