package com.app.acessibilidade.api.domain.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.acessibilidade.api.domain.model.Avaliacao;
import com.app.acessibilidade.api.domain.dto.outuput.*;


@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao,Long> {
    Avaliacao findById(long id);

     @Query("SELECT new com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_Avaliacao_DTO(a.id, a.comentario,a.estrelas,a.dt_avaliacao,a.usuario,a.local) FROM Avaliacao a")
    List<OUTPUT_Avaliacao_DTO> findAllAvaliacoes();
}
