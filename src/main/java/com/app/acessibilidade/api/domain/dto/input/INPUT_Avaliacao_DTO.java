package com.app.acessibilidade.api.domain.dto.input;

import java.sql.Date;

import com.app.acessibilidade.api.domain.model.Local;
import com.app.acessibilidade.api.domain.model.Usuario;

public record INPUT_Avaliacao_DTO(String comentario,String estrelas,Date dt_avaliacao,Usuario usuario,Local local) {
    
}
