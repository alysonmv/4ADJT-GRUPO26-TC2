package com.fiap.parquimetro_api.domain.recibo.controller;

import com.fiap.parquimetro_api.domain.recibo.dto.ReciboDTO;
import com.fiap.parquimetro_api.domain.recibo.service.ReciboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recibos")
public class ReciboController {
    @Autowired
    private ReciboService reciboService;

    @GetMapping
    public ResponseEntity<List<ReciboDTO>> getAll() {
        return ResponseEntity.ok(reciboService.findALL());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ReciboDTO>> getById(@PathVariable long idRecibo) {
        return ResponseEntity.ok(reciboService.findById(idRecibo));
    }

    @PostMapping
    public ResponseEntity<ReciboDTO> create(@RequestBody ReciboDTO reciboDTO) {
        return ResponseEntity.ok(reciboService.save(reciboDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long idRecibo) {
        reciboService.delete(idRecibo);
        return ResponseEntity.noContent().build();
    }
}
