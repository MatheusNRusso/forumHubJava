package com.mylocal.forumhub.controller;

import com.mylocal.forumhub.dto.UsuarioCreateDto;
import com.mylocal.forumhub.dto.UsuarioShowDto;
import com.mylocal.forumhub.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioShowDto> create(@RequestBody @Valid UsuarioCreateDto usuarioCreateDto, UriComponentsBuilder uriBuilder) {
        var usuario = usuarioService.cadastrar(usuarioCreateDto);
        var uri = uriBuilder.path("usuarios/{id}").buildAndExpand(usuario.id()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioShowDto>> findAll(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity
                .ok(usuarioService
                        .listarUsuarios(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioShowDto> findById(@PathVariable Long id) {
        return ResponseEntity
                .ok(usuarioService.buscarPorId(id));
    }

    @GetMapping("/ativos")
    public ResponseEntity<Page<UsuarioShowDto>> findAllAtctivos(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(usuarioService.listarUsuariosAtivos(pageable));
    }

    @GetMapping("/inativos")
    public ResponseEntity<Page<UsuarioShowDto>> findAllDeactivate(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(usuarioService.listarUsuariosInativos(pageable));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.desativar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/ativar")
    @Transactional
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        usuarioService.ativar(id);
        return ResponseEntity.noContent().build();
    }



}

