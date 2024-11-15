package com.app.acessibilidade.api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.acessibilidade.api.domain.model.Usuario;
import com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_usuario_DTO;  // Corrigir importação para o pacote correto
import com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_usuario_DTO;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Verifique se o método está correto e a consulta personalizada no @Query
    @Query("SELECT new com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_usuario_DTO(u.id, u.nome, u.email, u.senha) FROM Usuario u")
    List<OUTPUT_usuario_DTO> findAllUsuarios();
}
