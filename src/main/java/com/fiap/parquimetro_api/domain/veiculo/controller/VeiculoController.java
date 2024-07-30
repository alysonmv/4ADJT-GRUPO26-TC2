package com.fiap.parquimetro_api.domain.veiculo.controller;

import com.fiap.parquimetro_api.domain.veiculo.dto.VeiculoDTO;
import com.fiap.parquimetro_api.domain.veiculo.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<VeiculoDTO> cadastrarVeiculo(@RequestBody VeiculoDTO veiculoDTO, @RequestParam String cpf){
        return veiculoService.cadastrar(cpf, veiculoDTO);
    }
}
