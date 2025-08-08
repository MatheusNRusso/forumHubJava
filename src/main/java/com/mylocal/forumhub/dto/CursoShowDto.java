package com.mylocal.forumhub.dto;

import com.mylocal.forumhub.model.Categoria;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CursoShowDto(
        @NotNull
        Long id,
        String nome,
        Categoria categoria,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao) {
}
