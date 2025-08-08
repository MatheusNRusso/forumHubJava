package com.mylocal.forumhub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AlunoCreateDto(
        @NotBlank
        String nome,

        @NotBlank
        @Pattern(
                regexp = "^\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}$",
                message = "Telefone deve estar no formato (99) 99999-9999"
        )
        String telefone,
        @NotBlank
        @Pattern(
                regexp = "^\\d{11}$",
                message = "CPF deve conter exatamente 11 dígitos numéricos, sem pontos ou traços"
        )
        String cpf,

        @NotNull
        Long usuarioId) {}
