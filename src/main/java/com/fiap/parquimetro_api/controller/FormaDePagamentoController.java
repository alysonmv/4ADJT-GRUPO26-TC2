package com.fiap.parquimetro_api.controller;

import com.fiap.parquimetro_api.domain.dto.FormaDePagamentoDTO;
import com.fiap.parquimetro_api.service.FormaDePagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/formas-pagamento")
public class FormaDePagamentoController {
    @Autowired
    private FormaDePagamentoService formaDePagamentoService;

    @GetMapping
    public ResponseEntity<List<FormaDePagamentoDTO>> getAll() {
        return ResponseEntity.ok(formaDePagamentoService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<FormaDePagamentoDTO>> getById(@PathVariable Long idFormaDePagamento) {
        return ResponseEntity.ok(formaDePagamentoService.findById(idFormaDePagamento));
    }

    @PostMapping
    public ResponseEntity<FormaDePagamentoDTO> create(@RequestBody FormaDePagamentoDTO formaDePagamentoDTO) {
        return ResponseEntity.ok(formaDePagamentoService.save(formaDePagamentoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FormaDePagamentoDTO> delete(@PathVariable Long idFormaDePagamento) {
        formaDePagamentoService.delete(idFormaDePagamento);
        return ResponseEntity.noContent().build();
    }
}
