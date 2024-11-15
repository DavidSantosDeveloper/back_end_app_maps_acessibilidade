package com.app.acessibilidade.api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_Foto_DTO;
import com.app.acessibilidade.api.domain.model.*;

@Repository
public interface FotoRepository extends JpaRepository<Foto,Long> {
   Foto findById(long id);

     @Query("SELECT new com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_Foto_DTO(f.id,f.imagem) FROM Foto f")
    List<OUTPUT_Foto_DTO> findAllFotos();
}