package com.app.acessibilidade.api.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.acessibilidade.api.domain.dto.input.INPUT_TipoAcessibilidade_DTO;
import com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_TipoAcessibilidade_DTO;
import com.app.acessibilidade.api.domain.model.TipoAcessibilidade;
import com.app.acessibilidade.api.domain.repository.TipoAcessibilidadeRepository;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Service
public class TipoAcessibilidadeService {

    private TipoAcessibilidadeRepository tipoAcessibilidadeRepository;

    public OUTPUT_TipoAcessibilidade_DTO findById(Long tipoAcessibilidadeId) {
        TipoAcessibilidade tipoAcessibilidade = tipoAcessibilidadeRepository.findById(tipoAcessibilidadeId)
            .orElseThrow(() -> new RuntimeException("Tipo de acessibilidade não encontrado"));
        
        return new OUTPUT_TipoAcessibilidade_DTO(tipoAcessibilidade.getId(), tipoAcessibilidade.getNome(), tipoAcessibilidade.getDescricao());
    }

    public List<OUTPUT_TipoAcessibilidade_DTO> listarTodos() {
        return tipoAcessibilidadeRepository.findAllTipoAcessibilidades();
    }

    @Transactional
    public OUTPUT_TipoAcessibilidade_DTO salvar(INPUT_TipoAcessibilidade_DTO tipoAcessibilidadeDTO) {
        TipoAcessibilidade tipoAcessibilidade = new TipoAcessibilidade(null, tipoAcessibilidadeDTO.nome(), tipoAcessibilidadeDTO.descricao(), null);
        TipoAcessibilidade tipoAcessibilidadeSalvo = tipoAcessibilidadeRepository.save(tipoAcessibilidade);
        return new OUTPUT_TipoAcessibilidade_DTO(tipoAcessibilidadeSalvo.getId(), tipoAcessibilidadeSalvo.getNome(), tipoAcessibilidadeSalvo.getDescricao());
    }

    @Transactional
    public OUTPUT_TipoAcessibilidade_DTO editar(Long tipoAcessibilidadeId, INPUT_TipoAcessibilidade_DTO tipoAcessibilidadeDTO) {
        TipoAcessibilidade tipoAcessibilidade = tipoAcessibilidadeRepository.findById(tipoAcessibilidadeId)
            .orElseThrow(() -> new RuntimeException("Tipo de acessibilidade não encontrado"));
        
        tipoAcessibilidade.setNome(tipoAcessibilidadeDTO.nome());
        tipoAcessibilidade.setDescricao(tipoAcessibilidadeDTO.descricao());
        
        TipoAcessibilidade tipoAcessibilidadeSalvo = tipoAcessibilidadeRepository.save(tipoAcessibilidade);
        return new OUTPUT_TipoAcessibilidade_DTO(tipoAcessibilidadeSalvo.getId(), tipoAcessibilidadeSalvo.getNome(), tipoAcessibilidadeSalvo.getDescricao());
    }

    @Transactional
    public void excluir(Long tipoAcessibilidadeId) {
        tipoAcessibilidadeRepository.deleteById(tipoAcessibilidadeId);
    }
}
