package com.fiap.parquimetro_api.domain.estacionamento.controller;

import com.fiap.parquimetro_api.domain.estacionamento.dto.EstacionamentoDTO;
import com.fiap.parquimetro_api.domain.estacionamento.service.EstacionamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estacionamentos")
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
    @PostMapping
    public ResponseEntity<EstacionamentoDTO> create(@RequestBody EstacionamentoDTO estacionamentoDTO) {
        return ResponseEntity.ok(estacionamentoService.save(estacionamentoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long idEstacionamento) {
        estacionamentoService.delete(idEstacionamento);
        return ResponseEntity.noContent().build();
    }
}
