package com.app.acessibilidade.api.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.acessibilidade.api.domain.dto.*;
import com.app.acessibilidade.api.domain.dto.input.INPUT_Foto_DTO;
import com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_Foto_DTO;
import com.app.acessibilidade.api.domain.exception.*;
import com.app.acessibilidade.api.domain.model.*;
import com.app.acessibilidade.api.domain.repository.*;


import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class FotoService {
    private FotoRepository fotoRepository;
    
      public OUTPUT_Foto_DTO findById(Long fotoId){
        Optional<Foto> buscaPelaFoto=fotoRepository.findById(fotoId);
        if (buscaPelaFoto.isEmpty()) {
            return null;
        }
        Foto foto=buscaPelaFoto.get();
        return new OUTPUT_Foto_DTO(foto.getId(), foto.getImagem());

    }
    public List<OUTPUT_Foto_DTO> ListarFoto(){
        return fotoRepository.findAllFotos();
    }
    @Transactional
    public OUTPUT_Foto_DTO salvar(INPUT_Foto_DTO fotoDTO){
       
       Foto foto_sem_id=new Foto(null,fotoDTO.imagem(), null);
       Foto foto_com_id=fotoRepository.save(foto_sem_id);
        return new OUTPUT_Foto_DTO(foto_com_id.getId(), foto_com_id.getImagem());
    }
    @Transactional
    public OUTPUT_Foto_DTO editar(INPUT_Foto_DTO fotoDTO){
        Foto foto_sem_id=new Foto(null,fotoDTO.imagem(), null);
        Foto foto_com_id=fotoRepository.save(foto_sem_id);
         return new OUTPUT_Foto_DTO(foto_com_id.getId(),foto_com_id.getImagem());
     }
    @Transactional
    public void excluir(Long fotoId){
        fotoRepository.deleteById(fotoId);
    }
}