package com.app.acessibilidade.api.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.acessibilidade.api.domain.dto.input.INPUT_ItemAcessibilidade_DTO;
import com.app.acessibilidade.api.domain.dto.outuput.*;
import com.app.acessibilidade.api.domain.model.ItemAcessibilidade;
import com.app.acessibilidade.api.domain.repository.ItemAcessibilidadeRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ItemAcessibilidadeService {

    private final ItemAcessibilidadeRepository itemAcessibilidadeRepository;

    public OUTPUT_ItemAcessibilidade_DTO findById(Long itemAcessibilidadeId) {
        Optional<ItemAcessibilidade> buscaPeloItemAcessibilidade = itemAcessibilidadeRepository.findById(itemAcessibilidadeId);
        if (buscaPeloItemAcessibilidade.isEmpty()) {
            return null;
        }
        ItemAcessibilidade itemAcessibilidade = buscaPeloItemAcessibilidade.get();
        return new OUTPUT_ItemAcessibilidade_DTO(itemAcessibilidade.getId(), itemAcessibilidade.getLocal(), itemAcessibilidade.getTipoAcessibilidade());
    }

    public List<OUTPUT_ItemAcessibilidade_DTO> listarItemAcessibilidade() {
        return itemAcessibilidadeRepository.findAllItemAcessibilidade();
    }

    @Transactional
    public OUTPUT_ItemAcessibilidade_DTO salvar(INPUT_ItemAcessibilidade_DTO itemAcessibilidadeDTO) {
        ItemAcessibilidade itemAcessibilidadeSemId = new ItemAcessibilidade(null, itemAcessibilidadeDTO.local(), itemAcessibilidadeDTO.tipoAcessibilidade());
        ItemAcessibilidade itemAcessibilidadeComId = itemAcessibilidadeRepository.save(itemAcessibilidadeSemId);
        return new OUTPUT_ItemAcessibilidade_DTO(itemAcessibilidadeComId.getId(), itemAcessibilidadeComId.getLocal(), itemAcessibilidadeComId.getTipoAcessibilidade());
    }

    @Transactional
    public OUTPUT_ItemAcessibilidade_DTO editar(INPUT_ItemAcessibilidade_DTO itemAcessibilidadeDTO) {
        ItemAcessibilidade itemAcessibilidadeSemId = new ItemAcessibilidade(null, itemAcessibilidadeDTO.local(), itemAcessibilidadeDTO.tipoAcessibilidade());
        ItemAcessibilidade itemAcessibilidadeComId = itemAcessibilidadeRepository.save(itemAcessibilidadeSemId);
        return new OUTPUT_ItemAcessibilidade_DTO(itemAcessibilidadeComId.getId(), itemAcessibilidadeComId.getLocal(), itemAcessibilidadeComId.getTipoAcessibilidade());
    }

    @Transactional
    public void excluir(Long itemAcessibilidadeId) {
        itemAcessibilidadeRepository.deleteById(itemAcessibilidadeId);
    }
}
