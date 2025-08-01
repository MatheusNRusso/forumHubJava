package com.mylocal.forumhub.dto;

import jakarta.validation.constraints.NotNull;

public record UsuarioShowDto(
        @NotNull
        Long id,
        String email,
        boolean ativo
) {
}
