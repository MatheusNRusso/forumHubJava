package com.mylocal.forumhub.controller;

import com.mylocal.forumhub.dto.ClienteCreateDto;
import com.mylocal.forumhub.dto.ClienteShowDto;
import com.mylocal.forumhub.service.ClienteService;
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
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    @Transactional
    public ResponseEntity<ClienteShowDto> create(@RequestBody @Valid ClienteCreateDto clienteCreateDto, UriComponentsBuilder uriBuilder) {
        var cliente = clienteService.cadastrar(clienteCreateDto);
        var uri = uriBuilder.path("clientes/{id}").buildAndExpand(cliente.id()).toUri();
        return ResponseEntity.created(uri).body(cliente);

    }
    @GetMapping
    public ResponseEntity<Page<ClienteShowDto>> findAll(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        return ResponseEntity.ok(clienteService.listarClientes(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteShowDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @GetMapping("/ativos")
    public ResponseEntity<Page<ClienteShowDto>> findAllAtctivos(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        return ResponseEntity.ok(clienteService.listarClientesAtivos(pageable));
    }

    @GetMapping("/inativos")
    public ResponseEntity<Page<ClienteShowDto>> findAllDeactivate(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        return ResponseEntity.ok(clienteService.listarClientesInativos(pageable));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        clienteService.desativarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/ativar")
    @Transactional
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        clienteService.ativarCliente(id);
        return ResponseEntity.noContent().build();
    }

}