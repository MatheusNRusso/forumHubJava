package com.mylocal.forumhub.service;

import com.mylocal.forumhub.dto.UsuarioCreateDto;
import com.mylocal.forumhub.dto.UsuarioShowDto;
import com.mylocal.forumhub.mapper.UsuarioMapper;
import com.mylocal.forumhub.model.Usuario;
import com.mylocal.forumhub.repository.PerfilRepository;
import com.mylocal.forumhub.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    private final UsuarioMapper usuarioMapper;
    private final PerfilRepository perfilRepository;

    public UsuarioShowDto cadastrar(UsuarioCreateDto usuarioCreateDto) {
        var perfis = perfilRepository.findAllById(usuarioCreateDto.perfis());
        var usuario = Usuario.builder()
                .email(usuarioCreateDto.email())
                .senha(passwordEncoder.encode(usuarioCreateDto.senha()))
                .ativo(true)
                .perfis(perfis)
                .build();
        var usuarioNovo = usuarioRepository.save(usuario);

        return usuarioMapper.toDto(usuarioNovo);
    }

    public Page<UsuarioShowDto> listarUsuarios(Pageable pageable) {
        return usuarioRepository
                .findAll(pageable)
                .map(usuarioMapper::toDto);

    }
    public UsuarioShowDto buscarPorId(Long id) {
        return usuarioRepository
                .findById(id)
                .map(usuarioMapper::toDto)
                .orElse(null);
    }

    public Page<UsuarioShowDto> listarUsuariosAtivos(Pageable pageable) {
        return usuarioRepository.findAllByAtivoIsTrue(pageable).map(usuarioMapper::toDto);
    }

    public Page<UsuarioShowDto> listarUsuariosInativos(Pageable pageable) {
        return usuarioRepository.findAllByAtivoIsFalse(pageable).map(usuarioMapper::toDto);
    }

    public void desativar(Long id) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        usuario.setAtivo(false);
        usuarioRepository.save(usuario);
    }

    public void ativar(Long id) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        usuario.setAtivo(true);
        usuarioRepository.save(usuario);
    }

}
