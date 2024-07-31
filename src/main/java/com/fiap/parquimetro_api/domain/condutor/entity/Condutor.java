package com.fiap.parquimetro_api.domain.condutor.entity;

import com.fiap.parquimetro_api.domain.endereco.entity.Endereco;
import com.fiap.parquimetro_api.domain.formadepagamento.model.FormaDePagamento;
import com.fiap.parquimetro_api.domain.veiculo.entity.Veiculo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.text.Normalizer;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Condutores")
public class Condutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_condutor")
    private Long idCondutor;

    @Column(name = "nome")
    @NotBlank(message = "O nome não pode ser nulo ou branco")
    private String nome;

    @Column(name = "cpf")
    @NotBlank(message = "O cpf não pode ser nulo ou branco")
    //@CPF
    private String cpf;

    @Column(name = "email")
    @NotBlank(message = "O email não pode ser nulo ou branco")
    //@Email
    private String email;

    @Column(name = "telefone")
    @NotBlank(message = "O telefone não pode ser nulo ou branco")
    //@Pattern(regexp = "(\\d{2}) \\d{4}-\\d{4}")
    private String telefone;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @Column(name = "formaDePagamento")
    private FormaDePagamento formaDePagamento;

    @OneToMany(mappedBy="condutor")
    private List<Veiculo> listVeiculos;

    @Enumerated(EnumType.STRING)
    @Column(name="tipo_pagamento")
    @NotBlank(message = "Forma de pagamento é obrigatório")
    private TipoPagamento tipoPagamento;

}
