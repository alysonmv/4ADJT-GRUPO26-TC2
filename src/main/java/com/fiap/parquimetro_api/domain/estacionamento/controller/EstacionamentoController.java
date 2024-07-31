package com.fiap.parquimetro_api.domain.estacionamento.controller;

import com.fiap.parquimetro_api.domain.estacionamento.dto.EstacionamentoDTO;
import com.fiap.parquimetro_api.domain.estacionamento.model.TipoDeEstacionamento;
import com.fiap.parquimetro_api.domain.estacionamento.service.EstacionamentoService;
import com.fiap.parquimetro_api.domain.formadepagamento.model.FormaDePagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estacionamento")
public class EstacionamentoController {
    @Autowired
    private EstacionamentoService estacionamentoService;

    @GetMapping
    public ResponseEntity<List<EstacionamentoDTO>> getAll() {
        return ResponseEntity.ok(estacionamentoService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<EstacionamentoDTO>> getById(@PathVariable Long idEstacionamento) {
        return ResponseEntity.ok(estacionamentoService.findById(idEstacionamento));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<EstacionamentoDTO> create(LocalDateTime dataEntrada, LocalDateTime dataSaida, String cpf, String placa, FormaDePagamento formaDePagamento, TipoDeEstacionamento tipoDeEstacionamento) {
        //return ResponseEntity.ok(estacionamentoService.save(estacionamentoDTO));
        return estacionamentoService.cadastrar(dataEntrada, dataSaida, cpf, placa, formaDePagamento, tipoDeEstacionamento);
    }

}
