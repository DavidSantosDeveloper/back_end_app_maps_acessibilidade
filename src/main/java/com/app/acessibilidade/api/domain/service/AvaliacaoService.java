package com.app.acessibilidade.api.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.acessibilidade.api.domain.dto.*;
import com.app.acessibilidade.api.domain.dto.input.INPUT_Avaliacao_DTO;
import com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_Avaliacao_DTO;
import com.app.acessibilidade.api.domain.exception.*;
import com.app.acessibilidade.api.domain.model.*;
import com.app.acessibilidade.api.domain.repository.*;


import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AvaliacaoService {
    private AvaliacaoRepository avaliacaoRepository;
    
      public OUTPUT_Avaliacao_DTO findById(Long avaliacaoId){
        Optional<Avaliacao> buscaPelaAvaliacao=avaliacaoRepository.findById(avaliacaoId);
        if (buscaPelaAvaliacao.isEmpty()) {
            return null;
        }
        Avaliacao avaliacao=buscaPelaAvaliacao.get();
        return new OUTPUT_Avaliacao_DTO(avaliacao.getId(), avaliacao.getComentario(), avaliacao.getEstrelas(), avaliacao.getDt_avaliacao(), avaliacao.getUsuario(), avaliacao.getLocal());

    }
    public List<OUTPUT_Avaliacao_DTO> ListarAvaliacao(){
        return avaliacaoRepository.findAllAvaliacoes();
    }
    @Transactional
    public OUTPUT_Avaliacao_DTO salvarAvaliacao(INPUT_Avaliacao_DTO avaliacaoDTO){
       
       Avaliacao avaliacao_sem_id=new Avaliacao(null,avaliacaoDTO.comentario(), avaliacaoDTO.estrelas(), avaliacaoDTO.dt_avaliacao(), avaliacaoDTO.usuario(), avaliacaoDTO.local(), null);
       Avaliacao avaliacao_com_id=avaliacaoRepository.save(avaliacao_sem_id);
        return new OUTPUT_Avaliacao_DTO(avaliacao_com_id.getId(), avaliacao_com_id.getComentario(), avaliacao_com_id.getEstrelas(), avaliacao_com_id.getDt_avaliacao(), avaliacao_com_id.getUsuario(), avaliacao_com_id.getLocal());
    }
    @Transactional
    public OUTPUT_Avaliacao_DTO editarAvaliacao(INPUT_Avaliacao_DTO avaliacaoDTO){
        Avaliacao avaliacao_sem_id=new Avaliacao(null,avaliacaoDTO.comentario(), avaliacaoDTO.estrelas(), avaliacaoDTO.dt_avaliacao(), avaliacaoDTO.usuario(), avaliacaoDTO.local(), null);
        Avaliacao avaliacao_com_id=avaliacaoRepository.save(avaliacao_sem_id);
         return new OUTPUT_Avaliacao_DTO(avaliacao_com_id.getId(), avaliacao_com_id.getComentario(), avaliacao_com_id.getEstrelas(), avaliacao_com_id.getDt_avaliacao(), avaliacao_com_id.getUsuario(), avaliacao_com_id.getLocal());
     }
    @Transactional
    public void excluirAvaliacao(Long avaliacaoId){
        avaliacaoRepository.deleteById(avaliacaoId);
    }
}