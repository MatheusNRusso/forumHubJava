package com.mylocal.forumhub.service;

import com.mylocal.forumhub.dto.UsuarioCreateDto;
import com.mylocal.forumhub.dto.UsuarioShowDto;
import com.mylocal.forumhub.mapper.UsuarioMapper;
import com.mylocal.forumhub.model.Usuario;
import com.mylocal.forumhub.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    private final UsuarioMapper usuarioMapper;

    public UsuarioShowDto cadastrar(UsuarioCreateDto usuarioCreateDto) {

        var usuario = Usuario.builder()
                .email(usuarioCreateDto.email())
                .senha(passwordEncoder.encode(usuarioCreateDto.senha()))
                .ativo(true)
                .build();

        var usuarioNovo = usuarioRepository.save(usuario);

        return usuarioMapper.toDto(usuarioNovo);
    }


}
