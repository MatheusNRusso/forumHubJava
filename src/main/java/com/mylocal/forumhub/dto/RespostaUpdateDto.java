package com.mylocal.forumhub.dto;

import jakarta.validation.constraints.NotBlank;

public record RespostaUpdateDto(

        @NotBlank
        String mensagem) {
}
