package com.app.acessibilidade.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.app.acessibilidade.api.domain.dto.input.INPUT_Avaliacao_DTO;
import com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_Avaliacao_DTO;
import com.app.acessibilidade.api.domain.exception.*;
import com.app.acessibilidade.api.domain.model.*;
import com.app.acessibilidade.api.domain.repository.*;


import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AvaliacaoService {
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;
    
      
     public List<OUTPUT_Avaliacao_DTO> filtrarAvaliacoes(Long idLocal, String estrelas) {
        // Obtém todas as avaliações
        List<OUTPUT_Avaliacao_DTO> todasAvaliacoes = ListarAvaliacao();

        // Aplica a filtragem
        return todasAvaliacoes.stream()
                .filter(avaliacao -> (idLocal == null || avaliacao.id_Local().equals(idLocal)))
                .filter(avaliacao -> (estrelas == null || avaliacao.estrelas().equals(estrelas)))
                .collect(Collectors.toList());
    }

    public List<OUTPUT_Avaliacao_DTO> ListarAvaliacao2() {
        return avaliacaoRepository.findAll()
                .stream()
                .map(avaliacao -> new OUTPUT_Avaliacao_DTO(
                        avaliacao.getId(),
                        avaliacao.getComentario(),
                        avaliacao.getEstrelas(),
                        avaliacao.getDt_avaliacao(),
                        avaliacao.getUsuario().getId(),
                        avaliacao.getLocal() != null ? avaliacao.getLocal().getId() : null // Extrai apenas o ID
                ))
                .collect(Collectors.toList());
    }
    
   
    
    
    public OUTPUT_Avaliacao_DTO findById(Long avaliacaoId){
        Optional<Avaliacao> buscaPelaAvaliacao=avaliacaoRepository.findById(avaliacaoId);
        if (buscaPelaAvaliacao.isEmpty()) {
            return null;
        }
        Avaliacao avaliacao=buscaPelaAvaliacao.get();
        var usuario=(avaliacao.getUsuario()!=null)? avaliacao.getUsuario().getId() : null  ;
        return new OUTPUT_Avaliacao_DTO(avaliacao.getId(), avaliacao.getComentario(), avaliacao.getEstrelas(), avaliacao.getDt_avaliacao(), usuario, avaliacao.getLocal().getId());

    }
    public List<OUTPUT_Avaliacao_DTO> ListarAvaliacao(){
        return avaliacaoRepository.findAllAvaliacoes();
    }
    @Transactional
    public OUTPUT_Avaliacao_DTO salvarAvaliacao(INPUT_Avaliacao_DTO avaliacaoDTO){
       
       Avaliacao avaliacao_sem_id=new Avaliacao(null,avaliacaoDTO.comentario(), avaliacaoDTO.estrelas(), avaliacaoDTO.dt_avaliacao(), avaliacaoDTO.usuario(), avaliacaoDTO.local(), null);
       Avaliacao avaliacao_com_id=avaliacaoRepository.save(avaliacao_sem_id);

       var usuario=(avaliacao_com_id.getUsuario()!=null)? avaliacao_com_id.getUsuario().getId() : null  ;

        return new OUTPUT_Avaliacao_DTO(avaliacao_com_id.getId(), avaliacao_com_id.getComentario(), avaliacao_com_id.getEstrelas(), avaliacao_com_id.getDt_avaliacao(), usuario, avaliacao_com_id.getLocal().getId());
    }
    @Transactional
    public OUTPUT_Avaliacao_DTO editarAvaliacao(INPUT_Avaliacao_DTO avaliacaoDTO){
        Avaliacao avaliacao_sem_id=new Avaliacao(null,avaliacaoDTO.comentario(), avaliacaoDTO.estrelas(), avaliacaoDTO.dt_avaliacao(), avaliacaoDTO.usuario(), avaliacaoDTO.local(), null);
        Avaliacao avaliacao_com_id=avaliacaoRepository.save(avaliacao_sem_id);
        var usuario=(avaliacao_com_id.getUsuario()!=null)? avaliacao_com_id.getUsuario().getId() : null  ;
         return new OUTPUT_Avaliacao_DTO(avaliacao_com_id.getId(), avaliacao_com_id.getComentario(), avaliacao_com_id.getEstrelas(), avaliacao_com_id.getDt_avaliacao(), usuario, avaliacao_com_id.getLocal().getId());
     }
    @Transactional
    public void excluirAvaliacao(Long avaliacaoId){
        avaliacaoRepository.deleteById(avaliacaoId);
    }
}