package com.fiap.parquimetro_api.domain.estacionamento.entity;

import com.fiap.parquimetro_api.domain.formadepagamento.entity.FormaDePagamento;
import com.fiap.parquimetro_api.domain.condutor.entity.Condutor;
import com.fiap.parquimetro_api.domain.veiculo.entity.Veiculo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Estacionamento")
public class Estacionamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_estacionamento")
    private Long idEstacionamento;
    @Column(name="inicio")
    private LocalDateTime inicio;
    @Column(name = "fim")
    private LocalDateTime fim;
    @ManyToOne
    @JoinColumn(name="id_veiculo")
    private Veiculo veiculo;
    @ManyToOne
    @JoinColumn(name="id_forma_pagamento")
    private FormaDePagamento formaDePagamento;
    @ManyToOne
    @JoinColumn(name="id_condutor")
    private Condutor condutor;

    public Condutor getCondutor() {
        return condutor;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }
}
