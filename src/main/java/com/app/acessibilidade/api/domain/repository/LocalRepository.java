package com.app.acessibilidade.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_Local_DTO;
import com.app.acessibilidade.api.domain.model.Local;

import java.util.List;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {

    // Query personalizada para listar os locais com os dados que você deseja no DTO
    @Query("SELECT new com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_Local_DTO(l.id, l.localizacao, l.latitude, l.longitude) FROM Local l")
    List<OUTPUT_Local_DTO> findAllLocais();
}
