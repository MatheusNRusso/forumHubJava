package com.mylocal.forumhub.controller;
import com.mylocal.forumhub.dto.UsuarioCreateDto;
import com.mylocal.forumhub.dto.UsuarioShowDto;
import com.mylocal.forumhub.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioShowDto> create(@RequestBody @Valid UsuarioCreateDto usuarioCreateDto, UriComponentsBuilder uriBuilder) {
        var usuario = usuarioService.cadastrar(usuarioCreateDto);
        var uri = uriBuilder.path("usuarios/{id}").buildAndExpand(usuario.id()).toUri();
        return ResponseEntity.created(uri).body(usuario);

    }
}
