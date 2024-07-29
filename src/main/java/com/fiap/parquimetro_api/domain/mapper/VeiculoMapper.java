package com.fiap.parquimetro_api.domain.mapper;

import com.fiap.parquimetro_api.domain.Veiculo;
import com.fiap.parquimetro_api.domain.dto.VeiculoDTO;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class VeiculoMapper {

    public static VeiculoDTO toDTO(Veiculo veiculo) {
        if (ObjectUtils.isEmpty(veiculo)) {
            return null;
        }
        VeiculoDTO dto = new VeiculoDTO();
        dto.setIdVeiculo(veiculo.getIdVeiculo());
        dto.setPlaca(veiculo.getPlaca());
        dto.setMarca(veiculo.getMarca());
        dto.setModelo(veiculo.getModelo());

        return dto;
    }

    public static List<VeiculoDTO> toListDTO(List<Veiculo> listVeiculo) {
        if (ObjectUtils.isEmpty(listVeiculo)) {
            return null;
        }
        List<VeiculoDTO> listDto = new ArrayList<>();
        listVeiculo.forEach(veiculo -> {
            VeiculoDTO veiculoDTO = new VeiculoDTO();
            veiculoDTO.setIdVeiculo(veiculo.getIdVeiculo());
            veiculoDTO.setPlaca(veiculo.getPlaca());
            veiculoDTO.setMarca(veiculo.getMarca());
            veiculoDTO.setModelo(veiculo.getModelo());
            listDto.add(veiculoDTO);
        });

        return listDto;
    }

    public static Veiculo toEntity(VeiculoDTO dto) {
        if (ObjectUtils.isEmpty(dto)) {
            return null;
        }
        Veiculo veiculo = new Veiculo();
        veiculo.setIdVeiculo(dto.getIdVeiculo());
        veiculo.setPlaca(dto.getPlaca());
        veiculo.setMarca(dto.getMarca());
        veiculo.setModelo(dto.getModelo());

        return veiculo;
    }

    public static List<Veiculo> toListEntity(List<VeiculoDTO> listDto) {
        if (ObjectUtils.isEmpty(listDto)) {
            return null;
        }
        List<Veiculo> listVeiculo = new ArrayList<>();
        listDto.forEach(veiculoDTO -> {
            Veiculo veiculo = new Veiculo();
            veiculo.setIdVeiculo(veiculoDTO.getIdVeiculo());
            veiculo.setPlaca(veiculoDTO.getPlaca());
            veiculo.setMarca(veiculoDTO.getMarca());
            veiculo.setModelo(veiculoDTO.getModelo());
            listVeiculo.add(veiculo);
        });

        return listVeiculo;
    }
}
