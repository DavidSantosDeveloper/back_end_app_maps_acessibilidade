package com.app.acessibilidade.api.api.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.acessibilidade.api.domain.dto.*;
import com.app.acessibilidade.api.domain.dto.input.INPUT_Avaliacao_DTO;
import com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_Avaliacao_DTO;
import com.app.acessibilidade.api.domain.exception.*;
import com.app.acessibilidade.api.domain.model.*;
import com.app.acessibilidade.api.domain.repository.*;
import com.app.acessibilidade.api.domain.service.*;

import jakarta.validation.Valid;

import lombok.AllArgsConstructor;

@AllArgsConstructor        // gera um construtor automaticamente
@RestController
@RequestMapping("/Avaliacao")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;
    @Autowired
    private AvaliacaoService avaliacaoService;


    @GetMapping("")
    public List<OUTPUT_Avaliacao_DTO> listar(){
     
        return avaliacaoService.ListarAvaliacao();
     }

     @GetMapping("/{avaliacaoId}")
     public ResponseEntity<OUTPUT_Avaliacao_DTO> buscarPorId(@PathVariable Long avaliacaoId){
        Optional<OUTPUT_Avaliacao_DTO> pesquisaPelaAvaliacao=Optional.ofNullable(avaliacaoService.findById(avaliacaoId));

        if(pesquisaPelaAvaliacao.isPresent()){
            return ResponseEntity.ok(pesquisaPelaAvaliacao.get()); 
        }
        else{
            return ResponseEntity.notFound().build();
        }
     }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
     public OUTPUT_Avaliacao_DTO cadrastrar(@Valid @RequestBody  INPUT_Avaliacao_DTO avaliacao_DTO){

        return avaliacaoService.salvarAvaliacao(avaliacao_DTO);
     }

     @PutMapping("/{avaliacaoId}")
     public ResponseEntity<OUTPUT_Avaliacao_DTO>atualizar(@PathVariable Long avaliacaoId,@Valid @RequestBody INPUT_Avaliacao_DTO avaliacao_DTO){
         if(avaliacaoRepository.existsById(avaliacaoId)==false){
             return ResponseEntity.notFound().build();
         }
         OUTPUT_Avaliacao_DTO avaliacao_editado=avaliacaoService.editarAvaliacao(avaliacao_DTO);
         return ResponseEntity.ok(avaliacao_editado);
     }
     @PatchMapping("/{avaliacaoId}")
     public ResponseEntity<OUTPUT_Avaliacao_DTO>atualizarParcial(@PathVariable Long avaliacaoId,@Valid @RequestBody INPUT_Avaliacao_DTO avaliacao_DTO){
        if(avaliacaoRepository.existsById(avaliacaoId)==false){
            return ResponseEntity.notFound().build();
        }
     
        OUTPUT_Avaliacao_DTO avaliacao_editado=avaliacaoService.editarAvaliacao(avaliacao_DTO);
        return ResponseEntity.ok(avaliacao_editado);
    }

     @DeleteMapping("/{avaliacaoId}")
     public ResponseEntity<Void> deletar(@PathVariable Long avaliacaoId){
        if(avaliacaoRepository.existsById(avaliacaoId)==false){
           return ResponseEntity.notFound().build();
        }

        avaliacaoService.excluirAvaliacao(avaliacaoId);
        // Executou com suceeso e resposta sem body (Ideal para metodo http delete)
        return ResponseEntity.noContent().build();
     }

     @ExceptionHandler(RegraDeNegocioException.class)
     public ResponseEntity<String> capturarExcecao(RegraDeNegocioException erro){
        return ResponseEntity.badRequest().body(erro.getMessage());
     }
}


