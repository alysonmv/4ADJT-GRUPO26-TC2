package com.fiap.parquimetro_api.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.parquimetro_api.domain.Condutor;
import com.fiap.parquimetro_api.domain.mapper.EnderecoMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CondutorDTO {

    @JsonIgnore
    private Long idCondutor;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private EnderecoDTO enderecoDTO;

    public CondutorDTO(Condutor condutor){
        this.idCondutor = condutor.getIdCondutor();
        this.nome = condutor.getNome();
        this.cpf = condutor.getCpf();
        this.email = condutor.getEmail();
        this.telefone = condutor.getTelefone();
        this.enderecoDTO = EnderecoMapper.toDTO(condutor.getEndereco());
    }

}
