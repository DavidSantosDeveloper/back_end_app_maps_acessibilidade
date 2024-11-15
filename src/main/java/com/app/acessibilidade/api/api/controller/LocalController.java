package com.app.acessibilidade.api.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.acessibilidade.api.domain.dto.input.INPUT_Local_DTO;
import com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_Local_DTO;
import com.app.acessibilidade.api.domain.service.LocalService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/Local")
public class LocalController {

    private final LocalService localService;

    @GetMapping("")
    public List<OUTPUT_Local_DTO> listar() {
        return localService.listarLocais();
    }
    @GetMapping("/coordenadas")
    public ResponseEntity<List<OUTPUT_Local_DTO>> buscarPorCoordenadas(
        @RequestParam String latitude, 
        @RequestParam String longitude) {
        
        List<OUTPUT_Local_DTO> locais = localService.buscarPorCoordenadas(latitude, longitude);
        
        if (locais.isEmpty()) {
            return ResponseEntity.noContent().build();  // Retorna 204 caso não haja locais encontrados
        }
    
        return ResponseEntity.ok(locais);  // Retorna 200 com os locais encontrados
        }


    @GetMapping("/{localId}")
    public ResponseEntity<OUTPUT_Local_DTO> buscarPorId(@PathVariable Long localId) {
        OUTPUT_Local_DTO localDTO = localService.findById(localId);
        if (localDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(localDTO);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public OUTPUT_Local_DTO cadastrar(@Valid @RequestBody INPUT_Local_DTO localDTO) {
        return localService.salvar(localDTO);
    }

    @PutMapping("/{localId}")
    public ResponseEntity<OUTPUT_Local_DTO> atualizar(@PathVariable Long localId, @Valid @RequestBody INPUT_Local_DTO localDTO) {
        OUTPUT_Local_DTO localEditado = localService.editar(localDTO);
        return ResponseEntity.ok(localEditado);
    }

    @DeleteMapping("/{localId}")
    public ResponseEntity<Void> deletar(@PathVariable Long localId) {
        localService.excluir(localId);
        return ResponseEntity.noContent().build();
    }
}
