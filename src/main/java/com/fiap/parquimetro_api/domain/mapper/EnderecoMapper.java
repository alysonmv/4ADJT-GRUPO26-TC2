package com.fiap.parquimetro_api.domain.mapper;

import com.fiap.parquimetro_api.domain.Endereco;
import com.fiap.parquimetro_api.domain.dto.EnderecoDTO;
import com.fiap.parquimetro_api.domain.dto.EnderecoUpdateDTO;
import org.springframework.util.ObjectUtils;

public class EnderecoMapper {

    public static EnderecoDTO toDTO(Endereco endereco) {
        if (ObjectUtils.isEmpty(endereco)) {
            return null;
        }
        EnderecoDTO dto = new EnderecoDTO();
        dto.setIdEndereco(endereco.getIdEndereco());
        dto.setLogradouro(endereco.getLogradouro());
        dto.setNumero(endereco.getNumero());
        dto.setComplemento(endereco.getComplemento());
        dto.setBairro(endereco.getBairro());
        dto.setCidade(endereco.getCidade());
        dto.setEstado(endereco.getEstado());
        dto.setCep(endereco.getCep());

        return dto;
    }

    public static EnderecoUpdateDTO toDTOUpdate(Endereco endereco) {
        if (ObjectUtils.isEmpty(endereco)) {
            return null;
        }
        EnderecoUpdateDTO dto = new EnderecoUpdateDTO();
        dto.setIdEndereco(endereco.getIdEndereco());
        dto.setLogradouro(endereco.getLogradouro());
        dto.setNumero(endereco.getNumero());
        dto.setComplemento(endereco.getComplemento());
        dto.setBairro(endereco.getBairro());
        dto.setCidade(endereco.getCidade());
        dto.setEstado(endereco.getEstado());
        dto.setCep(endereco.getCep());

        return dto;
    }

    public static Endereco toEntity(EnderecoDTO dto) {
        if (ObjectUtils.isEmpty(dto)) {
            return null;
        }
        Endereco endereco = new Endereco();
        endereco.setIdEndereco(dto.getIdEndereco());
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setNumero(dto.getNumero());
        endereco.setComplemento(dto.getComplemento());
        endereco.setBairro(dto.getBairro());
        endereco.setCidade(dto.getCidade());
        endereco.setEstado(dto.getEstado());
        endereco.setCep(dto.getCep());

        return endereco;
    }
}
