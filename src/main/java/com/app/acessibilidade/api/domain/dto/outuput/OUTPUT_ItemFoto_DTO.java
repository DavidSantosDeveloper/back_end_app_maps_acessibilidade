package com.app.acessibilidade.api.domain.dto.outuput;

import com.app.acessibilidade.api.domain.model.Avaliacao;
import com.app.acessibilidade.api.domain.model.Foto;

public record OUTPUT_ItemFoto_DTO(Long id,Avaliacao avaliacao,Foto foto) {
    
}