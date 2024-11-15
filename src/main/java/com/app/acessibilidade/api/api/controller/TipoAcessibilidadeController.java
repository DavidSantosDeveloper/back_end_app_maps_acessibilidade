package com.app.acessibilidade.api.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.acessibilidade.api.domain.dto.input.INPUT_TipoAcessibilidade_DTO;
import com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_TipoAcessibilidade_DTO;
import com.app.acessibilidade.api.domain.service.TipoAcessibilidadeService;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/tipoAcessibilidade")
public class TipoAcessibilidadeController {

    @Autowired
    private TipoAcessibilidadeService tipoAcessibilidadeService;

    @GetMapping("")
    public List<OUTPUT_TipoAcessibilidade_DTO> listarTodos() {
        return tipoAcessibilidadeService.listarTodos();
    }

    @GetMapping("/{tipoAcessibilidadeId}")
    public ResponseEntity<OUTPUT_TipoAcessibilidade_DTO> buscarPorId(@PathVariable Long tipoAcessibilidadeId) {
        OUTPUT_TipoAcessibilidade_DTO tipoAcessibilidadeDTO = tipoAcessibilidadeService.findById(tipoAcessibilidadeId);
        return ResponseEntity.ok(tipoAcessibilidadeDTO);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public OUTPUT_TipoAcessibilidade_DTO cadastrar(@RequestBody INPUT_TipoAcessibilidade_DTO tipoAcessibilidadeDTO) {
        return tipoAcessibilidadeService.salvar(tipoAcessibilidadeDTO);
    }

    @PutMapping("/{tipoAcessibilidadeId}")
    public ResponseEntity<OUTPUT_TipoAcessibilidade_DTO> atualizar(@PathVariable Long tipoAcessibilidadeId, @RequestBody INPUT_TipoAcessibilidade_DTO tipoAcessibilidadeDTO) {
        OUTPUT_TipoAcessibilidade_DTO tipoAcessibilidadeEditado = tipoAcessibilidadeService.editar(tipoAcessibilidadeId, tipoAcessibilidadeDTO);
        return ResponseEntity.ok(tipoAcessibilidadeEditado);
    }

    @DeleteMapping("/{tipoAcessibilidadeId}")
    public ResponseEntity<Void> deletar(@PathVariable Long tipoAcessibilidadeId) {
        tipoAcessibilidadeService.excluir(tipoAcessibilidadeId);
        return ResponseEntity.noContent().build();
    }
}
