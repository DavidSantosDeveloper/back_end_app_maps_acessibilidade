package com.app.acessibilidade.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_ItemFoto_DTO;
import com.app.acessibilidade.api.domain.model.ItemFoto;

import java.util.List;

@Repository
public interface ItemFotoRepository extends JpaRepository<ItemFoto, Long> {

    // Busca um item de foto pelo ID
    ItemFoto findById(long id);

    // Query personalizada para retornar todos os ItemFoto em formato de DTO
    @Query("SELECT new com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_ItemFoto_DTO(i.id, i.avaliacao, i.foto) FROM ItemFoto i")
    List<OUTPUT_ItemFoto_DTO> findAllItemFotos();
}
