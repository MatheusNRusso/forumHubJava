package com.mylocal.forumhub.service;

import com.mylocal.forumhub.dto.AlunoCreateDto;
import com.mylocal.forumhub.dto.AlunoShowDto;
import com.mylocal.forumhub.mapper.AlunoMapper;
import com.mylocal.forumhub.model.Aluno;
import com.mylocal.forumhub.repository.AlunoRepository;
import com.mylocal.forumhub.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlunoService {


    private final AlunoRepository alunoRepository;

    private final AlunoMapper alunoMapper;

    private final UsuarioRepository usuarioRepository;

    public AlunoShowDto cadastrar(AlunoCreateDto dto) {
        var usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        var aluno = Aluno.builder()
                .nome(dto.nome())
                .telefone(dto.telefone())
                .cpf(dto.cpf())
                .usuario(usuario)
                .ativo(true)
                .build();
        var alunoNovo =  alunoRepository.save(aluno);
        return alunoMapper.toDto(alunoNovo);
    }

    public Page<AlunoShowDto> show(Pageable pageable) {
        return alunoRepository
                .findAll(pageable)
                .map(alunoMapper::toDto);
    }

    public AlunoShowDto buscarPorId(Long id) {
        return alunoRepository.findById(id)
                .map(alunoMapper::toDto).orElse(null);
    }
    public Page<AlunoShowDto> listarAlunosAtivos(Pageable pageable) {
        return alunoRepository.findAllByAtivoIsTrue(pageable).map(alunoMapper::toDto);
    }

    public Page<AlunoShowDto> listarAlunosInativos(Pageable pageable) {
        return alunoRepository.findAllByAtivoIsFalse(pageable).map(alunoMapper::toDto);
    }

    public void desativarAluno(Long id) {
        var aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        aluno.setAtivo(false);
        alunoRepository.save(aluno);
    }

    public void ativarAluno(Long id) {
        var aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        aluno.setAtivo(true);
        alunoRepository.save(aluno);
    }

}
