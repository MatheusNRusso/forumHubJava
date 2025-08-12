package com.mylocal.forumhub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RespostaCreateDto(

        @NotBlank
        String mensagem,

        @NotNull
        Long topicoId,

        @NotNull
        Long autorId
) {}
