package com.fiap.parquimetro_api.domain.veiculo.service;

import com.fiap.parquimetro_api.domain.condutor.entity.Condutor;
import com.fiap.parquimetro_api.domain.veiculo.entity.Veiculo;
import com.fiap.parquimetro_api.domain.veiculo.dto.VeiculoDTO;
import com.fiap.parquimetro_api.domain.veiculo.mapper.VeiculoMapper;
import com.fiap.parquimetro_api.domain.condutor.repository.CondutorRepository;
import com.fiap.parquimetro_api.domain.veiculo.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    CondutorRepository condutorRepository;

    @Transactional
    public ResponseEntity<VeiculoDTO> cadastrar (String cpf, VeiculoDTO veiculoDTO){
        if(ObjectUtils.isEmpty(veiculoDTO)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Optional<Condutor> condutor = condutorRepository.findByCpf(cpf);

        if(ObjectUtils.isEmpty(condutor)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<Veiculo> veiculoList = condutor.get().getListVeiculos();

        if (veiculoList.isEmpty()){
            veiculoList = new ArrayList<>();
            veiculoList.add(VeiculoMapper.toEntity(veiculoDTO));
        } else {
            if (!veiculoList.contains(VeiculoMapper.toEntity(veiculoDTO))){
                veiculoList.add(VeiculoMapper.toEntity(veiculoDTO));
            }
        }

        condutor.get().setListVeiculos(veiculoList);
        condutorRepository.save(condutor.get());

        Veiculo veiculoEncontrado = veiculoRepository.findByPlaca(veiculoDTO.getPlaca());

        if(veiculoEncontrado == null){
            Veiculo veiculo = new Veiculo();
            veiculo.setCondutor(condutor.get());
            veiculo.setPlaca(veiculoDTO.getPlaca());
            veiculo.setMarca(veiculoDTO.getMarca());
            veiculo.setModelo(veiculoDTO.getModelo());

            return ResponseEntity.status(HttpStatus.CREATED).body(new VeiculoDTO(veiculoRepository.save(veiculo)));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    public ResponseEntity<VeiculoDTO> consultar (String placa){
        if (ObjectUtils.isEmpty(placa)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Veiculo veiculoEncontrado = veiculoRepository.findByPlaca(placa);
        if (ObjectUtils.isEmpty(veiculoEncontrado)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(VeiculoMapper.toDTO(veiculoEncontrado));
    }

    public ResponseEntity<List<VeiculoDTO>> listar () {
        return ResponseEntity.status(HttpStatus.OK).body(VeiculoMapper.toListDTO(veiculoRepository.findAll()));
    }

}
