package com.fiap.parquimetro_api.domain.veiculo.controller;

import com.fiap.parquimetro_api.domain.veiculo.dto.VeiculoDTO;
import com.fiap.parquimetro_api.domain.veiculo.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<VeiculoDTO> cadastrar (@RequestBody VeiculoDTO veiculoDTO, @RequestParam String cpf){
        return veiculoService.cadastrar(cpf, veiculoDTO);
    }

    @GetMapping ("/listar/{placa}")
    public ResponseEntity<VeiculoDTO> consultar (@PathVariable String placa){
        return veiculoService.consultar(placa);
    }

    @GetMapping ("/listar")
    public ResponseEntity<List<VeiculoDTO>> listar (){
        return veiculoService.listar();
    }

    @PutMapping("/atualizar")
    public ResponseEntity<VeiculoDTO> atualizar (@RequestBody VeiculoDTO veiculoDTO, @RequestParam String cpf){
        return veiculoService.atualizar(veiculoDTO, cpf);
    }
}
