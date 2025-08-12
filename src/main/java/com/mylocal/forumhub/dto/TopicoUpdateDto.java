package com.mylocal.forumhub.dto;

import jakarta.validation.constraints.NotBlank;

public record TopicoUpdateDto(

        @NotBlank
        String titulo,

        @NotBlank
        String mensagem) {
}
