package com.mylocal.forumhub.controller;

import com.mylocal.forumhub.dto.ClienteCreateDto;
import com.mylocal.forumhub.dto.ClienteShowDto;
import com.mylocal.forumhub.model.Cliente;
import com.mylocal.forumhub.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}