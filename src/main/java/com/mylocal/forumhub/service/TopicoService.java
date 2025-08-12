package com.mylocal.forumhub.service;

import com.mylocal.forumhub.dto.TopicoCreateDto;
import com.mylocal.forumhub.dto.TopicoDetailDto;
import com.mylocal.forumhub.dto.TopicoShowDto;
import com.mylocal.forumhub.dto.TopicoUpdateDto;
import com.mylocal.forumhub.mapper.TopicoMapper;
import com.mylocal.forumhub.model.Topico;
import com.mylocal.forumhub.repository.TopicoRepository;
import com.mylocal.forumhub.repository.CursoRepository;
import com.mylocal.forumhub.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicoService {

    private final TopicoRepository topicoRepository;
    private final TopicoMapper topicoMapper;
    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;

    public TopicoShowDto cadastrar(TopicoCreateDto dto) {
        var curso = cursoRepository.findById(dto.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        var autor = usuarioRepository.findById(dto.autorId())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        var topico = Topico.builder()
                .titulo(dto.titulo())
                .mensagem(dto.mensagem())
                .curso(curso)
                .autor(autor)
                .status(dto.status())
                .ativo(true)
                .build();

        return topicoMapper.toDto(topicoRepository.save(topico));
    }


    public Page<TopicoShowDto> show(Pageable pageable) {
        return topicoRepository.findAll(pageable).map(topicoMapper::toDto);
    }

    public TopicoShowDto updateTopico(Long id, TopicoUpdateDto updateDto) {

        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topico não encontrado"));

        topico.setTitulo(updateDto.titulo());
        topico.setMensagem(updateDto.mensagem());

        return topicoMapper.toDto(topicoRepository.save(topico));
    }

    public TopicoDetailDto buscarPorId(Long id) {
        return topicoRepository.findById(id)
                .map(topicoMapper::toDetailDto)
                .orElse(null);
    }

    public Page<TopicoShowDto> listarAtivos(Pageable pageable) {
        return topicoRepository.findAllByAtivoIsTrue(pageable).map(topicoMapper::toDto);
    }

    public Page<TopicoShowDto> listarInativos(Pageable pageable) {
        return topicoRepository.findAllByAtivoIsFalse(pageable).map(topicoMapper::toDto);
    }

    public void desativar(Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));
        topico.setAtivo(false);
        topicoRepository.save(topico);
    }

    public void ativar(Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));
        topico.setAtivo(true);
        topicoRepository.save(topico);
    }
}
