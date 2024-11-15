package com.app.acessibilidade.api.domain.dto.outuput;

import com.app.acessibilidade.api.domain.model.Local;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OUTPUT_Local_DTO {
    private Long id;
    private String localizacao;
    private String latitude;
    private String longitude;

    // Construtor que converte um objeto Local em OUTPUT_Local_DTO
    public OUTPUT_Local_DTO(Local local) {
        this.id = local.getId();
        this.localizacao = local.getLocalizacao();
        this.latitude = local.getLatitude();
        this.longitude = local.getLongitude();
    }
    
}
