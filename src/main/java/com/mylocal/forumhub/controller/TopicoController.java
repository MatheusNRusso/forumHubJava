package com.mylocal.forumhub.controller;

import com.mylocal.forumhub.dto.TopicoCreateDto;
import com.mylocal.forumhub.dto.TopicoDetailDto;
import com.mylocal.forumhub.dto.TopicoShowDto;

import com.mylocal.forumhub.dto.TopicoUpdateDto;
import com.mylocal.forumhub.mapper.TopicoMapper;
import com.mylocal.forumhub.service.TopicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService topicoService;
    private final TopicoMapper topicoMapper;

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoShowDto> create(@RequestBody @Valid TopicoCreateDto topicoCreateDto, UriComponentsBuilder uriBuilder) {
        var topico = topicoService.cadastrar(topicoCreateDto);
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.id()).toUri();
        return ResponseEntity.created(uri).body(topico);

    }
    @GetMapping
    public ResponseEntity<Page<TopicoShowDto>> findAll(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(topicoService.show(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDetailDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(topicoService.buscarPorId(id));
    }

    @GetMapping("/ativos")
    public ResponseEntity<Page<TopicoShowDto>> findAllAtctivos(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(topicoService.listarAtivos(pageable));
    }

    @GetMapping("/inativos")
    public ResponseEntity<Page<TopicoShowDto>> findAllDeactivate(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(topicoService.listarInativos(pageable));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        topicoService.desativar(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoShowDto> update(@PathVariable Long id, @RequestBody @Valid TopicoUpdateDto topicoUpdateDto ) {
        return ResponseEntity.ok(topicoService.updateTopico(id, topicoUpdateDto));
    }

    @PutMapping("/{id}/ativar")
    @Transactional
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        topicoService.ativar(id);
        return ResponseEntity.noContent().build();
    }

}
