package com.mylocal.forumhub.dto;

import com.mylocal.forumhub.model.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursoCreateDto(

        @NotBlank
        String nome,

        @NotNull
        Categoria categoria
) {}
