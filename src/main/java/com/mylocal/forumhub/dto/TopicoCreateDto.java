package com.mylocal.forumhub.dto;

import com.mylocal.forumhub.model.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoCreateDto(

        @NotBlank
        String titulo,

        @NotBlank
        String mensagem,

        @NotNull
        Long autorId,

        @NotNull
        Long cursoId,

        @NotNull
        Estado status
) {}
