package com.mylocal.forumhub.controller;

import com.mylocal.forumhub.dto.RespostaCreateDto;
import com.mylocal.forumhub.dto.RespostaShowDto;
import com.mylocal.forumhub.dto.RespostaUpdateDto;
import com.mylocal.forumhub.service.RespostaService;
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
@RequestMapping("/respostas")
public class RespostaController {

    private final RespostaService respostaService;

    @PostMapping
    @Transactional
    public ResponseEntity<RespostaShowDto> create(@RequestBody @Valid RespostaCreateDto respostaCreateDto, UriComponentsBuilder uriBuilder) {
        var resposta = respostaService.cadastrar(respostaCreateDto);
        var uri = uriBuilder.path("/respostas/{id}").buildAndExpand(resposta.id()).toUri();
        return ResponseEntity.created(uri).body(resposta);

    }
    @GetMapping
    public ResponseEntity<Page<RespostaShowDto>> findAll(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(respostaService.show(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespostaShowDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(respostaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<RespostaShowDto> update(@PathVariable Long id, @RequestBody @Valid RespostaUpdateDto respostaUpdateDto) {
        return ResponseEntity.ok(respostaService.updateResposta(id, respostaUpdateDto));
    }



    @GetMapping("/ativos")
    public ResponseEntity<Page<RespostaShowDto>> findAllAtctivos(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(respostaService.listarAtivas(pageable));
    }

    @GetMapping("/inativos")
    public ResponseEntity<Page<RespostaShowDto>> findAllDeactivate(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(respostaService.listarInativas(pageable));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        respostaService.desativar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/ativar")
    @Transactional
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        respostaService.ativar(id);
        return ResponseEntity.noContent().build();
    }

}