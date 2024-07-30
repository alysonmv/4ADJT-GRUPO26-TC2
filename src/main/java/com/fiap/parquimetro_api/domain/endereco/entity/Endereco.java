package com.fiap.parquimetro_api.domain.endereco.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Long idEndereco;

    @Column(name = "logradouro")
    @NotBlank(message = "O Logradouro não pode ser nulo ou branco")
    private String logradouro;

    @Column(name = "numero")
    @NotBlank(message = "O número não pode ser nulo ou branco")
    private String numero;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "bairro")
    @NotBlank(message = "O bairro não pode ser nulo ou branco")
    private String bairro;

    @Column(name = "cidade")
    @NotBlank(message = "A cidade não pode ser nulo ou branco")
    private String cidade;

    @Column(name = "estado")
    @NotBlank(message = "O estado não pode ser nulo ou branco")
    private String estado;

    @Column(name = "cep")
    @NotBlank(message = "O cep não pode ser nulo ou branco")
    //@Pattern(regexp = "\\d{5}-\\d{3}")
    private String cep;

}
