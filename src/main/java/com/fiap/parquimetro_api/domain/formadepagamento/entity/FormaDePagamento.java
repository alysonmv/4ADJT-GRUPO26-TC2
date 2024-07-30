package com.fiap.parquimetro_api.domain.formadepagamento.entity;


import com.fiap.parquimetro_api.domain.condutor.entity.Condutor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "forma_de_pagamento")
public class FormaDePagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_forma_pagamento")
    private Long idFormaDePagamento;
    @Column(name="tipo_pagamento")
    @NotBlank(message="O tipo de pagamento não pode ser nulo ou branco")
    private String tipoPagamento;
    @ManyToOne
    @JoinColumn(name="id_condutor")
    private Condutor condutor;

}
