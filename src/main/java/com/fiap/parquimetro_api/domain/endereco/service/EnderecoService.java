package com.fiap.parquimetro_api.domain.endereco.service;

import com.fiap.parquimetro_api.domain.endereco.entity.Endereco;
import com.fiap.parquimetro_api.domain.endereco.dto.EnderecoDTO;
import com.fiap.parquimetro_api.domain.endereco.dto.EnderecoUpdateDTO;
import com.fiap.parquimetro_api.domain.endereco.mapper.EnderecoMapper;
import com.fiap.parquimetro_api.domain.endereco.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional
    public EnderecoDTO cadastrar(EnderecoDTO enderecoDTO) {
        if (ObjectUtils.isEmpty(enderecoDTO)) {
            return null;
        }
        Endereco endereco = new Endereco();
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setComplemento(enderecoDTO.getComplemento());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setEstado(enderecoDTO.getEstado());
        endereco.setCep(enderecoDTO.getCep());

        return new EnderecoDTO(enderecoRepository.save(endereco));
    }

    public ResponseEntity<EnderecoDTO> consultarEnderecoPorNumeroCep (String numero, String cep){
        if (ObjectUtils.isEmpty(numero) && ObjectUtils.isEmpty(cep)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (ObjectUtils.isEmpty(enderecoRepository.findByNumeroAndCep(numero, cep))){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(EnderecoMapper.toDTO(enderecoRepository.findByNumeroAndCep(numero, cep)));
    }

    public ResponseEntity<EnderecoDTO> atualizar (EnderecoUpdateDTO condutorDTO){
        Optional<Endereco> enderecoEncontrado = enderecoRepository.findById(condutorDTO.getIdEndereco());
        if (ObjectUtils.isEmpty(enderecoEncontrado)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Endereco endereco = new Endereco();
        endereco.setIdEndereco(condutorDTO.getIdEndereco());
        endereco.setLogradouro(condutorDTO.getLogradouro());
        endereco.setNumero(condutorDTO.getNumero());
        endereco.setComplemento(condutorDTO.getComplemento());
        endereco.setBairro(condutorDTO.getBairro());
        endereco.setCidade(condutorDTO.getCidade());
        endereco.setEstado(condutorDTO.getEstado());
        endereco.setCep(condutorDTO.getCep());

        return ResponseEntity.status(HttpStatus.OK).body(EnderecoMapper.toDTO(enderecoRepository.save(endereco)));
    }
}
