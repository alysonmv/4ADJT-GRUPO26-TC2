package com.fiap.parquimetro_api.domain.condutor.service;

import com.fiap.parquimetro_api.domain.condutor.entity.Condutor;
import com.fiap.parquimetro_api.domain.condutor.dto.CondutorDTO;
import com.fiap.parquimetro_api.domain.condutor.dto.CondutorUpdateDTO;
import com.fiap.parquimetro_api.domain.condutor.mapper.CondutorMapper;
import com.fiap.parquimetro_api.domain.endereco.mapper.EnderecoMapper;
import com.fiap.parquimetro_api.domain.condutor.repository.CondutorRepository;
import com.fiap.parquimetro_api.domain.endereco.service.EnderecoService;
import com.fiap.parquimetro_api.domain.veiculo.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class CondutorService {

    @Autowired
    private CondutorRepository condutorRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private VeiculoService veiculoService;

    @Transactional
    public ResponseEntity<CondutorDTO> cadastrar(CondutorDTO condutorDTO) {
        if (ObjectUtils.isEmpty(condutorDTO)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (!ObjectUtils.isEmpty(consultar(condutorDTO.getCpf()).getBody())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Condutor condutor = CondutorMapper.toEntity(condutorDTO);
        condutor.setEndereco(EnderecoMapper.toEntity(enderecoService.cadastrar(condutorDTO.getEnderecoDTO())));
        condutor.setFormaDePagamento(condutorDTO.getTipoPagamento());

        return ResponseEntity.status(HttpStatus.CREATED).body(new CondutorDTO(condutorRepository.save(condutor)));
    }

    public ResponseEntity<CondutorDTO> consultar (String cpf){
        if (ObjectUtils.isEmpty(cpf)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Optional<Condutor> condutorEncontrado = condutorRepository.findByCpf(cpf);
        if (ObjectUtils.isEmpty(condutorEncontrado)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(new CondutorDTO(condutorEncontrado.get()));
    }

    public ResponseEntity<List<CondutorDTO>> listar (){
        return ResponseEntity.status(HttpStatus.OK).body(CondutorMapper.toListDTO(condutorRepository.findAll()));
    }

    public ResponseEntity<CondutorDTO> atualizar (CondutorUpdateDTO condutorDTO){
        Optional<Condutor> condutorEncontrado = condutorRepository.findByCpf(condutorDTO.getCpf());
        if (ObjectUtils.isEmpty(condutorEncontrado)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Condutor condutor = new Condutor();
        condutor.setIdCondutor(condutorEncontrado.get().getIdCondutor());
        condutor.setNome(condutorDTO.getNome());
        condutor.setCpf(condutorDTO.getCpf());
        condutor.setTelefone(condutorDTO.getTelefone());
        condutor.setEmail(condutorDTO.getEmail());
        condutor.setTipoPagamento(condutorDTO.getTipoPagamento());
        condutor.setEndereco(EnderecoMapper.toEntity(enderecoService.atualizar(condutorDTO.getEnderecoUpdateDTO()).getBody()));
        condutor.setFormaDePagamento(condutorDTO.getFormaDePagamento());

        return ResponseEntity.status(HttpStatus.OK).body(CondutorMapper.toDto(condutorRepository.save(condutor)));
    }
}
