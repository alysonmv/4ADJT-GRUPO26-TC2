package com.fiap.parquimetro_api.domain.condutor.controller;

import com.fiap.parquimetro_api.domain.condutor.dto.CondutorDTO;
import com.fiap.parquimetro_api.domain.condutor.dto.CondutorUpdateDTO;
import com.fiap.parquimetro_api.domain.condutor.service.CondutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/condutor")
public class CondutorController {

    @Autowired
    private CondutorService condutorService;

    @PostMapping("/cadastrar")
    public ResponseEntity<CondutorDTO> cadastrarCondutor(@Valid @RequestBody CondutorDTO condutorDTO){
        return condutorService.cadastrar(condutorDTO);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CondutorDTO>> listarCondutores(){
        return condutorService.listar();
    }

    @GetMapping("/consultar/{cpf}")
    public ResponseEntity<CondutorDTO> consultarCondutor(@PathVariable String cpf){
        return condutorService.consultar(cpf);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<CondutorDTO> atualizarCondutor(@Valid @RequestBody CondutorUpdateDTO condutorDTO){
        return condutorService.atualizar(condutorDTO);
    }

}
