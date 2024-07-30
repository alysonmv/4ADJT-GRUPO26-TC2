package com.fiap.parquimetro_api.domain.condutor.mapper;

import com.fiap.parquimetro_api.domain.endereco.mapper.EnderecoMapper;
import com.fiap.parquimetro_api.domain.condutor.entity.Condutor;
import com.fiap.parquimetro_api.domain.condutor.dto.CondutorDTO;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class CondutorMapper {

    public static List<CondutorDTO> toListDTO(List<Condutor> condutorList) {
        if (ObjectUtils.isEmpty(condutorList)) {
            return null;
        }
        List<CondutorDTO> listDto = new ArrayList<>();
        condutorList.forEach(condutor -> {
            CondutorDTO condutorDTO = new CondutorDTO();
            condutorDTO.setIdCondutor(condutor.getIdCondutor());
            condutorDTO.setNome(condutor.getNome());
            condutorDTO.setCpf(condutor.getCpf());
            condutorDTO.setTelefone(condutor.getTelefone());
            condutorDTO.setEmail(condutor.getEmail());
            condutorDTO.setEnderecoDTO(EnderecoMapper.toDTO(condutor.getEndereco()));

            listDto.add(condutorDTO);
        });

        return listDto;
    }

    public static List<Condutor> toListEntity(List<CondutorDTO> condutorDTOList) {
        if (ObjectUtils.isEmpty(condutorDTOList)) {
            return null;
        }
        List<Condutor> condutorList = new ArrayList<>();
        condutorDTOList.forEach(condutorDTO -> {
            Condutor condutor = new Condutor();
            condutor.setIdCondutor(condutorDTO.getIdCondutor());
            condutor.setNome(condutorDTO.getNome());
            condutor.setCpf(condutorDTO.getCpf());
            condutor.setTelefone(condutorDTO.getTelefone());
            condutor.setEmail(condutorDTO.getEmail());
            condutor.setEndereco(EnderecoMapper.toEntity(condutorDTO.getEnderecoDTO()));

            condutorList.add(condutor);
        });

        return condutorList;
    }

    public static Condutor toEntity(CondutorDTO dto) {
        if (ObjectUtils.isEmpty(dto)) {
            return null;
        }
        Condutor condutor = new Condutor();
        condutor.setIdCondutor(dto.getIdCondutor());
        condutor.setNome(dto.getNome());
        condutor.setCpf(dto.getCpf());
        condutor.setTelefone(dto.getTelefone());
        condutor.setEmail(dto.getEmail());
        condutor.setEndereco(EnderecoMapper.toEntity(dto.getEnderecoDTO()));

        return condutor;
    }

    public static CondutorDTO toDto(Condutor condutor) {
        if (ObjectUtils.isEmpty(condutor)) {
            return null;
        }
        CondutorDTO condutorDTO = new CondutorDTO();
        condutorDTO.setIdCondutor(condutor.getIdCondutor());
        condutorDTO.setNome(condutor.getNome());
        condutorDTO.setCpf(condutor.getCpf());
        condutorDTO.setTelefone(condutor.getTelefone());
        condutorDTO.setEmail(condutor.getEmail());
        condutorDTO.setEnderecoDTO(EnderecoMapper.toDTO(condutor.getEndereco()));

        return condutorDTO;
    }

}
