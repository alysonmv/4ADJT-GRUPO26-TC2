package com.fiap.parquimetro_api.domain.estacionamento.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.parquimetro_api.domain.formadepagamento.model.FormaDePagamento;
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
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    @Column(name="inicio")
    private LocalDateTime inicio;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    @Column(name = "fim")
    private LocalDateTime fim;
    @ManyToOne
    @JoinColumn(name="id_veiculo")
    private Veiculo veiculo;
    @Column
    private FormaDePagamento formaDePagamento;
    @ManyToOne
    @JoinColumn(name="id_condutor")
    private Condutor condutor;
}
