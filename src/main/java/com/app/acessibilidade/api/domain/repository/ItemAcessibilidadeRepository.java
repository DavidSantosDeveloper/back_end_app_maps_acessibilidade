package com.app.acessibilidade.api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_ItemAcessibilidade_DTO;
import com.app.acessibilidade.api.domain.model.*;

@Repository
public interface ItemAcessibilidadeRepository extends JpaRepository<ItemAcessibilidade,Long> {
   ItemAcessibilidade findById(long id);

     @Query("SELECT new com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_ItemAcessibilidade_DTO(ia.id,ia.local.id,ia.tipoAcessibilidade.id) FROM ItemAcessibilidade ia")
    List<OUTPUT_ItemAcessibilidade_DTO> findAllItemAcessibilidade();
}