package com.mylocal.forumhub.dto;

import jakarta.validation.constraints.NotNull;

public record PerfilShowDto(
        @NotNull
        Long id,

        String nome,

        Integer nivel
) {
}
