package com.app.acessibilidade.api.api.controller;


import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.app.acessibilidade.api.domain.dto.input.INPUT_Avaliacao_DTO;
import com.app.acessibilidade.api.domain.dto.input.INPUT_Foto_DTO;
import com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_Avaliacao_DTO;
import com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_Foto_DTO;
import com.app.acessibilidade.api.domain.exception.*;
import com.app.acessibilidade.api.domain.model.*;
import com.app.acessibilidade.api.domain.repository.*;
import com.app.acessibilidade.api.domain.service.*;

import jakarta.validation.Valid;

import lombok.AllArgsConstructor;

@AllArgsConstructor        // gera um construtor automaticamente
@RestController
@RequestMapping("/Foto")

public class FotoController {
    @Autowired
    private FotoRepository fotoRepository;
    @Autowired
    private FotoService fotoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/upload")
   public ResponseEntity<List<OUTPUT_Foto_DTO>> cadastrar(@RequestParam("files") List<MultipartFile> files) {
    if (files == null || files.isEmpty()) {
        // Retorna uma resposta 400 se nenhuma foto for enviada
        return ResponseEntity.badRequest().build();
    }

    List<OUTPUT_Foto_DTO> fotosSalvas = new ArrayList<>(); // Lista para armazenar as fotos salvas

    try {
        for (MultipartFile file : files) {
            // Salva cada imagem e adiciona à lista de resultados
            OUTPUT_Foto_DTO fotoSalva = fotoService.salvarImagemBase64(file);
            fotosSalvas.add(fotoSalva);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(fotosSalvas);
    } catch (Exception e) {
        // Retorna uma resposta 400 em caso de erro
        return ResponseEntity.badRequest().build();
    }
}
    // public ResponseEntity<OUTPUT_Foto_DTO> cadastrar(@RequestParam("file") MultipartFile file) {
    //     try {
    //         // Converte a imagem para Base64 e salva
    //         OUTPUT_Foto_DTO fotoDTO = fotoService.salvarImagemBase64(file);
    //         return ResponseEntity.status(HttpStatus.CREATED).body(fotoDTO);
    //     } catch (Exception e) {
    //         return ResponseEntity.badRequest().body(null);
      
    //     }
    // }
    
    @GetMapping("")
    public List<OUTPUT_Foto_DTO> listar(){
     
        return fotoService.ListarFoto();
     }

     @GetMapping("/{fotoId}")
     public ResponseEntity<OUTPUT_Foto_DTO> buscarPorId(@PathVariable Long fotoId){
        Optional<OUTPUT_Foto_DTO> pesquisaPelaFoto=Optional.ofNullable(fotoService.findById(fotoId));

        if(pesquisaPelaFoto.isPresent()){
            return ResponseEntity.ok(pesquisaPelaFoto.get()); 
        }
        else{
            return ResponseEntity.notFound().build();
        }
     }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
     public OUTPUT_Foto_DTO cadrastrar(@Valid @RequestBody  INPUT_Foto_DTO foto_DTO){

        return fotoService.salvar(foto_DTO);
     }

     @PutMapping("/{fotoId}")
     public ResponseEntity<OUTPUT_Foto_DTO>atualizar(@PathVariable Long fotoId,@Valid @RequestBody INPUT_Foto_DTO foto_DTO){
         if(fotoRepository.existsById(fotoId)==false){
             return ResponseEntity.notFound().build();
         }
         OUTPUT_Foto_DTO foto_editado=fotoService.editar(foto_DTO);
         return ResponseEntity.ok(foto_editado);
     }
     @PatchMapping("/{fotoId}")
     public ResponseEntity<OUTPUT_Foto_DTO>atualizarParcial(@PathVariable Long fotoId,@Valid @RequestBody INPUT_Foto_DTO foto_DTO){
        if(fotoRepository.existsById(fotoId)==false){
            return ResponseEntity.notFound().build();
        }
     
        OUTPUT_Foto_DTO foto_editado=fotoService.editar(foto_DTO);
        return ResponseEntity.ok(foto_editado);
    }

     @DeleteMapping("/{fotoId}")
     public ResponseEntity<Void> deletar(@PathVariable Long fotoId){
        if(fotoRepository.existsById(fotoId)==false){
           return ResponseEntity.notFound().build();
        }

        fotoService.excluir(fotoId);
        // Executou com suceeso e resposta sem body (Ideal para metodo http delete)
        return ResponseEntity.noContent().build();
     }

     @ExceptionHandler(RegraDeNegocioException.class)
     public ResponseEntity<String> capturarExcecao(RegraDeNegocioException erro){
        return ResponseEntity.badRequest().body(erro.getMessage());
     }
}
