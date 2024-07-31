package com.fiap.parquimetro_api.domain.veiculo.entity;

import com.fiap.parquimetro_api.domain.condutor.entity.Condutor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_veiculo")
    private Long idVeiculo;

    @Column(name = "placa")
    @NotBlank(message = "A placa do veículo não pode ser nulo ou branco")
    private String placa;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modelo")
    private String modelo;

    @ManyToOne
    @JoinColumn(name="id_condutor")
    private Condutor condutor;

}
