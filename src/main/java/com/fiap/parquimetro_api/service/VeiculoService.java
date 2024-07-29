package com.fiap.parquimetro_api.service;

import com.fiap.parquimetro_api.domain.Condutor;
import com.fiap.parquimetro_api.domain.Veiculo;
import com.fiap.parquimetro_api.domain.dto.VeiculoDTO;
import com.fiap.parquimetro_api.domain.mapper.VeiculoMapper;
import com.fiap.parquimetro_api.repository.CondutorRepository;
import com.fiap.parquimetro_api.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

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

        Condutor condutor = condutorRepository.findByCpf(cpf);

        if(ObjectUtils.isEmpty(condutor)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<Veiculo> veiculoList = condutor.getListVeiculos();

        if (veiculoList.isEmpty()){
            veiculoList = new ArrayList<>();
            veiculoList.add(VeiculoMapper.toEntity(veiculoDTO));
        } else {
            if (!veiculoList.contains(VeiculoMapper.toEntity(veiculoDTO))){
                veiculoList.add(VeiculoMapper.toEntity(veiculoDTO));
            }
        }

        condutor.setListVeiculos(veiculoList);
        condutorRepository.save(condutor);

        Veiculo veiculoEncontrado = veiculoRepository.findByPlaca(veiculoDTO.getPlaca());

        if(veiculoEncontrado == null){
            Veiculo veiculo = new Veiculo();
            veiculo.setCondutor(condutor);
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

        return ResponseEntity.status(HttpStatus.OK).body(new VeiculoDTO(veiculoEncontrado));
    }
}
