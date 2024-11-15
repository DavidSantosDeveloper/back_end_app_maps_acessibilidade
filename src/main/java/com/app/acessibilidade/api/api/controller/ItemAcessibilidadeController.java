package com.app.acessibilidade.api.api.controller;

import com.app.acessibilidade.api.domain.dto.input.INPUT_ItemAcessibilidade_DTO;
import com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_ItemAcessibilidade_DTO;
import com.app.acessibilidade.api.domain.service.ItemAcessibilidadeService;
import com.app.acessibilidade.api.domain.repository.ItemAcessibilidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/itemAcessibilidade")
public class ItemAcessibilidadeController {

    @Autowired
    private ItemAcessibilidadeService itemAcessibilidadeService;
    
    @Autowired
    private ItemAcessibilidadeRepository itemAcessibilidadeRepository;

    @GetMapping("")
    public List<OUTPUT_ItemAcessibilidade_DTO> listar() {
        return itemAcessibilidadeService.listarItemAcessibilidade();
    }

    @GetMapping("/{itemAcessibilidadeId}")
    public ResponseEntity<OUTPUT_ItemAcessibilidade_DTO> buscarPorId(@PathVariable Long itemAcessibilidadeId) {
        OUTPUT_ItemAcessibilidade_DTO itemAcessibilidade = itemAcessibilidadeService.findById(itemAcessibilidadeId);
        if (itemAcessibilidade != null) {
            return ResponseEntity.ok(itemAcessibilidade);
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public OUTPUT_ItemAcessibilidade_DTO cadastrar(@Valid @RequestBody INPUT_ItemAcessibilidade_DTO itemAcessibilidadeDTO) {
        return itemAcessibilidadeService.salvar(itemAcessibilidadeDTO);
    }

    @PutMapping("/{itemAcessibilidadeId}")
    public ResponseEntity<OUTPUT_ItemAcessibilidade_DTO> atualizar(@PathVariable Long itemAcessibilidadeId, 
                                                                  @Valid @RequestBody INPUT_ItemAcessibilidade_DTO itemAcessibilidadeDTO) {
        if (!itemAcessibilidadeRepository.existsById(itemAcessibilidadeId)) {
            return ResponseEntity.notFound().build();
        }
        OUTPUT_ItemAcessibilidade_DTO itemAcessibilidadeEditado = itemAcessibilidadeService.editar(itemAcessibilidadeDTO);
        return ResponseEntity.ok(itemAcessibilidadeEditado);
    }

    @PatchMapping("/{itemAcessibilidadeId}")
    public ResponseEntity<OUTPUT_ItemAcessibilidade_DTO> atualizarParcial(@PathVariable Long itemAcessibilidadeId, 
                                                                           @Valid @RequestBody INPUT_ItemAcessibilidade_DTO itemAcessibilidadeDTO) {
        if (!itemAcessibilidadeRepository.existsById(itemAcessibilidadeId)) {
            return ResponseEntity.notFound().build();
        }
        OUTPUT_ItemAcessibilidade_DTO itemAcessibilidadeEditado = itemAcessibilidadeService.editar(itemAcessibilidadeDTO);
        return ResponseEntity.ok(itemAcessibilidadeEditado);
    }

    @DeleteMapping("/{itemAcessibilidadeId}")
    public ResponseEntity<Void> deletar(@PathVariable Long itemAcessibilidadeId) {
        if (!itemAcessibilidadeRepository.existsById(itemAcessibilidadeId)) {
            return ResponseEntity.notFound().build();
        }
        itemAcessibilidadeService.excluir(itemAcessibilidadeId);
        return ResponseEntity.noContent().build();
    }
}
