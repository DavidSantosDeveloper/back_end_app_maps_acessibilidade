package com.app.acessibilidade.api.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.acessibilidade.api.domain.dto.input.INPUT_Local_DTO;
import com.app.acessibilidade.api.domain.dto.input.INPUT_usuario_DTO;
import com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_Local_DTO;
import com.app.acessibilidade.api.domain.dto.outuput.OUTPUT_usuario_DTO;
import com.app.acessibilidade.api.domain.service.LocalService;
import com.app.acessibilidade.api.domain.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/Usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("")
    public List<OUTPUT_usuario_DTO> listar() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<OUTPUT_usuario_DTO> buscarPorId(@PathVariable Long usuarioId) {
        OUTPUT_usuario_DTO usuarioDTO = usuarioService.findById(usuarioId);
        if (usuarioDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarioDTO);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public OUTPUT_usuario_DTO cadastrar(@Valid @RequestBody INPUT_usuario_DTO usuarioDTO) {
        return usuarioService.salvar(usuarioDTO);
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<OUTPUT_usuario_DTO> atualizar(@PathVariable Long usuarioId, @Valid @RequestBody INPUT_usuario_DTO usuarioDTO) {
        OUTPUT_usuario_DTO localEditado = usuarioService.editar(usuarioId,usuarioDTO);
        return ResponseEntity.ok(localEditado);
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Void> deletar(@PathVariable Long usuarioId) {
        usuarioService.excluir(usuarioId);
        return ResponseEntity.noContent().build();
    }
}
