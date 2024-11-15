package com.app.acessibilidade.api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_TipoAcessibilidade_DTO;
import com.app.acessibilidade.api.domain.model.TipoAcessibilidade;

@Repository
public interface TipoAcessibilidadeRepository extends JpaRepository<TipoAcessibilidade, Long> {

    TipoAcessibilidade findById(long id);

    @Query("SELECT new com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_TipoAcessibilidade_DTO(t.id, t.nome, t.descricao) FROM TipoAcessibilidade t")
    List<OUTPUT_TipoAcessibilidade_DTO> findAllTipoAcessibilidades();
}
