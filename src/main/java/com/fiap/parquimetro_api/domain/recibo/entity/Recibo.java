package com.fiap.parquimetro_api.domain.recibo.entity;

import com.fiap.parquimetro_api.domain.estacionamento.entity.Estacionamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Recibo")
public class Recibo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_recibo")
    private int idRecibo;
    @Column(name = "data_emissao")
    private LocalDate dataEmissao;
    @Column(name="valor_total")
    private BigDecimal valorTotal;
    @ManyToOne
    @JoinColumn(name="id_estacionamento")
    private Estacionamento estacionamento;
}
