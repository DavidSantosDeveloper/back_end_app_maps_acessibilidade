package com.app.acessibilidade.api.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.acessibilidade.api.domain.dto.input.INPUT_Local_DTO;
import com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_Local_DTO;
import com.app.acessibilidade.api.domain.model.Local;
import com.app.acessibilidade.api.domain.repository.LocalRepository;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class LocalService {

    private final LocalRepository localRepository;

    public List<OUTPUT_Local_DTO> buscarPorCoordenadas(String latitude, String longitude) {
    // Obter os locais com as coordenadas fornecidas
    List<Local> locais = localRepository.findByLatitudeAndLongitude(latitude, longitude);
    
    // Criar uma lista de DTOs para os locais encontrados
    List<OUTPUT_Local_DTO> outputLocais = new ArrayList<>();
    
    // Iterar pelos locais e transformar cada Local em um OUTPUT_Local_DTO
    for (Local local : locais) {
        OUTPUT_Local_DTO localDTO = new OUTPUT_Local_DTO(local);
        outputLocais.add(localDTO);
    }
    
    return outputLocais;  // Retorna a lista de DTOs
   }

    
    


    // Método para buscar um Local pelo ID
    public OUTPUT_Local_DTO findById(Long localId) {
        Optional<Local> buscaPeloLocal = localRepository.findById(localId);
        if (buscaPeloLocal.isEmpty()) {
            return null;  // ou lançar uma exceção personalizada, caso necessário
        }
        Local local = buscaPeloLocal.get();
        return new OUTPUT_Local_DTO(local.getId(), local.getLocalizacao(), local.getLatitude(), local.getLongitude());
    }

    // Método para listar todos os Locais
    public List<OUTPUT_Local_DTO> listarLocais() {
        return localRepository.findAllLocais();
    }

    // Método para salvar um novo Local
    @Transactional
    public OUTPUT_Local_DTO salvar(INPUT_Local_DTO localDTO) {
        Local localSemId = new Local(null, localDTO.localizacao(), localDTO.latitude(), localDTO.longitude(), null, null);
        Local localComId = localRepository.save(localSemId);
        return new OUTPUT_Local_DTO(localComId.getId(), localComId.getLocalizacao(), localComId.getLatitude(), localComId.getLongitude());
    }

    // Método para editar um Local existente
    @Transactional
    public OUTPUT_Local_DTO editar(INPUT_Local_DTO localDTO) {
        Local localSemId = new Local(null, localDTO.localizacao(), localDTO.latitude(), localDTO.longitude(), null, null);
        Local localComId = localRepository.save(localSemId);
        return new OUTPUT_Local_DTO(localComId.getId(), localComId.getLocalizacao(), localComId.getLatitude(), localComId.getLongitude());
    }

    // Método para excluir um Local
    @Transactional
    public void excluir(Long localId) {
        localRepository.deleteById(localId);
    }
}
