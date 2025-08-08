package com.mylocal.forumhub.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PerfilCreateDto(
        @NotBlank
        String nome,

        @NotNull
        @Min(0)
        @Max(5)
        Integer nivel) {
}
