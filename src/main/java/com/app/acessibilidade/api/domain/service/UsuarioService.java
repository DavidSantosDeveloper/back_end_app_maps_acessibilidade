package com.app.acessibilidade.api.domain.service;

import com.app.acessibilidade.api.domain.dto.outuput.*;
import com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_usuario_DTO;
import com.app.acessibilidade.api.domain.dto.input.INPUT_usuario_DTO;
import com.app.acessibilidade.api.domain.model.Usuario;
import com.app.acessibilidade.api.domain.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;

    public OUTPUT_usuario_DTO findById(Long usuarioId) {
        Optional<Usuario> buscaPeloUsuario = usuarioRepository.findById(usuarioId);
        if (buscaPeloUsuario.isEmpty()) {
            return null;
        }
        Usuario usuario = buscaPeloUsuario.get();
        return new OUTPUT_usuario_DTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha());
    }

    public List<OUTPUT_usuario_DTO> listarUsuarios() {
        return usuarioRepository.findAllUsuarios();
    }

    @Transactional
    public OUTPUT_usuario_DTO salvar(INPUT_usuario_DTO usuarioDTO) {
        Usuario usuario = new Usuario(null, usuarioDTO.nome(), usuarioDTO.email(), usuarioDTO.senha(), null);
        Usuario usuarioComId = usuarioRepository.save(usuario);
        return new OUTPUT_usuario_DTO(usuarioComId.getId(), usuarioComId.getNome(), usuarioComId.getEmail(), usuarioComId.getSenha());
    }

    @Transactional
    public OUTPUT_usuario_DTO editar(Long usuarioId, INPUT_usuario_DTO usuarioDTO) {
        if (!usuarioRepository.existsById(usuarioId)) {
            return null;
        }
        Usuario usuario = new Usuario(usuarioId, usuarioDTO.nome(), usuarioDTO.email(), usuarioDTO.senha(), null);
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return new OUTPUT_usuario_DTO(usuarioAtualizado.getId(), usuarioAtualizado.getNome(), usuarioAtualizado.getEmail(), usuarioAtualizado.getSenha());
    }

    @Transactional
    public void excluir(Long usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }
}
