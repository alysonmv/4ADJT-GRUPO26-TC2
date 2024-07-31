package com.fiap.parquimetro_api.domain.condutor.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.parquimetro_api.domain.condutor.entity.TipoPagamento;
import com.fiap.parquimetro_api.domain.endereco.dto.EnderecoUpdateDTO;
import com.fiap.parquimetro_api.domain.condutor.entity.Condutor;
import com.fiap.parquimetro_api.domain.endereco.mapper.EnderecoMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CondutorUpdateDTO {

    @JsonIgnore
    private Long idCondutor;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private EnderecoUpdateDTO enderecoUpdateDTO;
    private TipoPagamento tipoPagamento;

    public CondutorUpdateDTO(Condutor condutor){
        this.idCondutor = condutor.getIdCondutor();
        this.nome = condutor.getNome();
        this.cpf = condutor.getCpf();
        this.email = condutor.getEmail();
        this.telefone = condutor.getTelefone();
        this.enderecoUpdateDTO = EnderecoMapper.toDTOUpdate(condutor.getEndereco());
        this.tipoPagamento = condutor.getTipoPagamento();
    }

}
