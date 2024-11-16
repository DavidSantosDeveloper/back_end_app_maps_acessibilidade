package com.app.acessibilidade.api.domain.dto.outuput;

import java.sql.Date;

import com.app.acessibilidade.api.domain.model.Local;
import com.app.acessibilidade.api.domain.model.Usuario;

public record OUTPUT_Avaliacao_DTO(Long id,String comentario,String estrelas,Date dt_avaliacao,Long id_usuario,Long id_Local) {
    
}
