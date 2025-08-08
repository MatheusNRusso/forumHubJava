package com.mylocal.forumhub.controller;

import com.mylocal.forumhub.dto.AlunoCreateDto;
import com.mylocal.forumhub.dto.AlunoShowDto;
import com.mylocal.forumhub.service.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    @PostMapping
    @Transactional
    public ResponseEntity<AlunoShowDto> create(@RequestBody @Valid AlunoCreateDto alunoCreateDto, UriComponentsBuilder uriBuilder) {
        var aluno = alunoService.cadastrar(alunoCreateDto);
        var uri = uriBuilder.path("/alunos/{id}").buildAndExpand(aluno.id()).toUri();
        return ResponseEntity.created(uri).body(aluno);

    }
    @GetMapping
    public ResponseEntity<Page<AlunoShowDto>> findAll(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        return ResponseEntity.ok(alunoService.show(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoShowDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.buscarPorId(id));
    }

    @GetMapping("/ativos")
    public ResponseEntity<Page<AlunoShowDto>> findAllAtctivos(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        return ResponseEntity.ok(alunoService.listarAlunosAtivos(pageable));
    }

    @GetMapping("/inativos")
    public ResponseEntity<Page<AlunoShowDto>> findAllDeactivate(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        return ResponseEntity.ok(alunoService.listarAlunosInativos(pageable));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        alunoService.desativarAluno(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/ativar")
    @Transactional
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        alunoService.ativarAluno(id);
        return ResponseEntity.noContent().build();
    }

}