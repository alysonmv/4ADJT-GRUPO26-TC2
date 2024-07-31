package com.fiap.parquimetro_api.domain.condutor.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.parquimetro_api.domain.endereco.dto.EnderecoDTO;
import com.fiap.parquimetro_api.domain.condutor.entity.Condutor;
import com.fiap.parquimetro_api.domain.endereco.mapper.EnderecoMapper;
import com.fiap.parquimetro_api.domain.formadepagamento.model.FormaDePagamento;
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
    private FormaDePagamento formaDePagamento;

    public CondutorDTO(Condutor condutor){
        this.idCondutor = condutor.getIdCondutor();
        this.nome = condutor.getNome();
        this.cpf = condutor.getCpf();
        this.email = condutor.getEmail();
        this.telefone = condutor.getTelefone();
        this.enderecoDTO = EnderecoMapper.toDTO(condutor.getEndereco());
        this.formaDePagamento = condutor.getFormaDePagamento();
    }

}
