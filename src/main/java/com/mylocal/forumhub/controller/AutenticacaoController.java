package com.mylocal.forumhub.controller;


import com.mylocal.forumhub.dto.AutenticacaoDto;
import com.mylocal.forumhub.dto.TokenResponseDto;
import com.mylocal.forumhub.model.Usuario;
import com.mylocal.forumhub.service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping
    public ResponseEntity<TokenResponseDto> login(@RequestBody @Valid AutenticacaoDto autenticacaoDto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(
                autenticacaoDto.email(), autenticacaoDto.senha()
        );

        var authentication = authenticationManager.authenticate(authenticationToken);
        var usuario = (Usuario) authentication.getPrincipal();
        var token = tokenService.generateToken(usuario);

        return ResponseEntity.ok(new TokenResponseDto(token));
    }

}
