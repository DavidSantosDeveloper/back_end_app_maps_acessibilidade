package com.app.acessibilidade.api.api.controller;

import com.app.acessibilidade.api.domain.dto.input.INPUT_Foto_DTO;
import com.app.acessibilidade.api.domain.dto.input.INPUT_ItemAcessibilidade_DTO;
import com.app.acessibilidade.api.domain.dto.input.INPUT_ItemFoto_DTO;
import com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_ItemAcessibilidade_DTO;
import com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_ItemFoto_DTO;
import com.app.acessibilidade.api.domain.service.ItemAcessibilidadeService;
import com.app.acessibilidade.api.domain.service.ItemFotoService;
import com.app.acessibilidade.api.domain.repository.ItemAcessibilidadeRepository;
import com.app.acessibilidade.api.domain.repository.ItemFotoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/itemFoto")
public class ItemFotoController {

    @Autowired
    private ItemFotoService itemFotoService;
    
    @Autowired
    private ItemFotoRepository itemFotoRepository;



    @GetMapping("")
    public ResponseEntity<List<OUTPUT_ItemFoto_DTO>> listarPorIdAvaliacao(
            @RequestParam(value = "id_avaliacao", required = false) Long idAvaliacao) {
    
        // Lista itens filtrados ou todos se idAvaliacao for nulo
        List<OUTPUT_ItemFoto_DTO> itens = itemFotoService.listarPorAvaliacao(idAvaliacao);
    
        if (itens.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 - Sem conteúdo
        }
        return ResponseEntity.ok(itens);
    }
    
    // @GetMapping("")
    // public List<OUTPUT_ItemFoto_DTO> listar() {
    //     return itemFotoService.listarItemFotos();
    // }

    @GetMapping("/{itemFotoId}")
    public ResponseEntity<OUTPUT_ItemFoto_DTO> buscarPorId(@PathVariable Long itemFotoId) {
        OUTPUT_ItemFoto_DTO itemFoto = itemFotoService.findById(itemFotoId);
        if (itemFoto != null) {
            return ResponseEntity.ok(itemFoto);
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public OUTPUT_ItemFoto_DTO cadastrar(@Valid @RequestBody INPUT_ItemFoto_DTO itemFotoDTO) {
        return itemFotoService.salvar(itemFotoDTO);
    }

    @PutMapping("/{itemFotoId}")
    public ResponseEntity<OUTPUT_ItemFoto_DTO> atualizar(@PathVariable Long itemFotoId, 
                                                                  @Valid @RequestBody INPUT_ItemFoto_DTO itemFotoDTO) {
        if (!itemFotoRepository.existsById(itemFotoId)) {
            return ResponseEntity.notFound().build();
        }
        OUTPUT_ItemFoto_DTO itemFoto = itemFotoService.editar(itemFotoDTO);
        return ResponseEntity.ok(itemFoto);
    }

    @PatchMapping("/{itemFotoId}")
    public ResponseEntity<OUTPUT_ItemFoto_DTO> atualizarParcial(@PathVariable Long itemFotoId, 
                                                                           @Valid @RequestBody INPUT_ItemFoto_DTO itemFotoDTO) {
        if (!itemFotoRepository.existsById(itemFotoId)) {
            return ResponseEntity.notFound().build();
        }
        OUTPUT_ItemFoto_DTO itemFoto = itemFotoService.editar(itemFotoDTO);
        return ResponseEntity.ok(itemFoto);
    }

    @DeleteMapping("/{itemFotoId}")
    public ResponseEntity<Void> deletar(@PathVariable Long itemFotoId) {
        if (!itemFotoRepository.existsById(itemFotoId)) {
            return ResponseEntity.notFound().build();
        }
        itemFotoService.excluir(itemFotoId);
        return ResponseEntity.noContent().build();
    }
}
