package com.mylocal.forumhub.dto;

import jakarta.validation.constraints.NotNull;

public record ClienteShowDto(
        @NotNull
        Long id,
        String nome,
        String telefone
) {}
