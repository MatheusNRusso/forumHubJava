package com.mylocal.forumhub.controller;

import com.mylocal.forumhub.dto.PerfilCreateDto;
import com.mylocal.forumhub.dto.PerfilShowDto;
import com.mylocal.forumhub.service.PerfilService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@RestController
@RequestMapping("/perfis")
public class PerfilController {

    private final PerfilService perfilService;

    @PostMapping
    public ResponseEntity<PerfilShowDto> createPerfil(
            @RequestBody @Valid PerfilCreateDto perfilCreateDto,
            UriComponentsBuilder uriBuilder) {

        var perfil = perfilService.create(perfilCreateDto);
        var uri = uriBuilder.path("/perfis/{id}").buildAndExpand(perfil.id()).toUri();
        return ResponseEntity.created(uri).body(perfil);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerfilShowDto> findById(@PathVariable Long id) {
        var perfil = perfilService.findById(id);
        return ResponseEntity.ok(perfil);
    }

    @GetMapping
    public ResponseEntity<Page<PerfilShowDto>> findAll(Pageable pageable) {
        var perfis = perfilService.findAll(pageable);
        return ResponseEntity.ok(perfis);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerfilShowDto> update(
            @PathVariable Long id,
            @RequestBody @Valid PerfilCreateDto perfilCreateDto) {

        var perfilAtualizado = perfilService.update(id, perfilCreateDto);
        return ResponseEntity.ok(perfilAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        perfilService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
