package com.mylocal.forumhub.dto;

import jakarta.validation.constraints.NotNull;

public record AlunoShowDto(
        @NotNull
        Long id,
        String nome,
        String telefone
) {}
