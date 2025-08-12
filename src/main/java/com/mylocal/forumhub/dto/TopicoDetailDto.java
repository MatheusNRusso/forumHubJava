package com.mylocal.forumhub.dto;

import com.mylocal.forumhub.model.Aluno;
import com.mylocal.forumhub.model.Estado;
import com.mylocal.forumhub.model.Resposta;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record TopicoDetailDto(
        @NotNull
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        String nomeAutor,
        Estado status,
        List<RespostaShowDto> respostas
) {}
