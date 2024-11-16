package com.app.acessibilidade.api.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.acessibilidade.api.domain.dto.input.*;
import com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_ItemFoto_DTO;
import com.app.acessibilidade.api.domain.model.ItemFoto;
import com.app.acessibilidade.api.domain.repository.ItemFotoRepository;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ItemFotoService {

    private final ItemFotoRepository itemFotoRepository;

    // Método para buscar um ItemFoto pelo ID
    public OUTPUT_ItemFoto_DTO findById(Long itemFotoId) {
        Optional<ItemFoto> buscaPeloItemFoto = itemFotoRepository.findById(itemFotoId);
        if (buscaPeloItemFoto.isEmpty()) {
            return null;  // ou lançar uma exceção personalizada, caso necessário
        }
        ItemFoto itemFoto = buscaPeloItemFoto.get();
        return new OUTPUT_ItemFoto_DTO(itemFoto.getId(), itemFoto.getAvaliacao().getId(), itemFoto.getFoto().getId());
    }

    // Método para listar todos os ItemFoto
    public List<OUTPUT_ItemFoto_DTO> listarItemFotos() {
        return itemFotoRepository.findAllItemFotos();
    }

    // Método para salvar um novo ItemFoto
    @Transactional
    public OUTPUT_ItemFoto_DTO salvar(INPUT_ItemFoto_DTO itemFotoDTO) {
        ItemFoto itemFotoSemId = new ItemFoto(null, itemFotoDTO.avaliacao(), itemFotoDTO.foto());
        ItemFoto itemFotoComId = itemFotoRepository.save(itemFotoSemId);
        return new OUTPUT_ItemFoto_DTO(itemFotoComId.getId(), itemFotoComId.getAvaliacao().getId(), itemFotoComId.getFoto().getId());
    }

    // Método para editar um ItemFoto existente
    @Transactional
    public OUTPUT_ItemFoto_DTO editar(INPUT_ItemFoto_DTO itemFotoDTO) {
        ItemFoto itemFotoSemId = new ItemFoto(null, itemFotoDTO.avaliacao(), itemFotoDTO.foto());
        ItemFoto itemFotoComId = itemFotoRepository.save(itemFotoSemId);
        return new OUTPUT_ItemFoto_DTO(itemFotoComId.getId(), itemFotoComId.getAvaliacao().getId(), itemFotoComId.getFoto().getId());
    }

    // Método para excluir um ItemFoto
    @Transactional
    public void excluir(Long itemFotoId) {
        itemFotoRepository.deleteById(itemFotoId);
    }
}
