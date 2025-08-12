package com.mylocal.forumhub.service;

import com.mylocal.forumhub.dto.RespostaCreateDto;
import com.mylocal.forumhub.dto.RespostaShowDto;
import com.mylocal.forumhub.dto.RespostaUpdateDto;
import com.mylocal.forumhub.mapper.RespostaMapper;
import com.mylocal.forumhub.model.Resposta;
import com.mylocal.forumhub.repository.RespostaRepository;
import com.mylocal.forumhub.repository.TopicoRepository;
import com.mylocal.forumhub.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RespostaService {

    private final RespostaRepository respostaRepository;
    private final TopicoRepository topicoRepository;
    private final RespostaMapper respostaMapper;
    private final UsuarioRepository usuarioRepository;

    public RespostaShowDto cadastrar(RespostaCreateDto dto) {
        var topico = topicoRepository.findById(dto.topicoId())
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));
        var autor = usuarioRepository.findById(dto.autorId())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        var resposta = Resposta.builder()
                .mensagem(dto.mensagem())
                .topico(topico)
                .autor(autor)
                .ativo(true)
                .build();

        var respostaNova = respostaRepository.save(resposta);
        return respostaMapper.toDto(respostaNova);
    }

    public Page<RespostaShowDto> show(Pageable pageable) {
        return respostaRepository.findAll(pageable).map(respostaMapper::toDto);
    }

    public RespostaShowDto buscarPorId(Long id) {
        return respostaRepository.findById(id)
                .map(respostaMapper::toDto)
                .orElse(null);
    }

    public RespostaShowDto updateResposta(Long id, RespostaUpdateDto respostaUpdateDto) {
        var resposta = respostaRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Resposta não encontarda"));
        resposta.setMensagem(respostaUpdateDto.mensagem());
        return respostaMapper.toDto(respostaRepository.save(resposta));
    }


    public Page<RespostaShowDto> listarAtivas(Pageable pageable) {
        return respostaRepository.findAllByAtivoIsTrue(pageable).map(respostaMapper::toDto);
    }

    public Page<RespostaShowDto> listarInativas(Pageable pageable) {
        return respostaRepository.findAllByAtivoIsFalse(pageable).map(respostaMapper::toDto);
    }

    public void desativar(Long id) {
        var resposta = respostaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resposta não encontrada"));
        resposta.setAtivo(false);
        respostaRepository.save(resposta);
    }

    public void ativar(Long id) {
        var resposta = respostaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resposta não encontrada"));
        resposta.setAtivo(true);
        respostaRepository.save(resposta);
    }
}
