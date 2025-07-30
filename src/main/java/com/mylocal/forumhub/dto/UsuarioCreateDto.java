package com.mylocal.forumhub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record UsuarioCreateDto(

        @NotBlank @Email
        String email,

        @NotBlank @Length(min = 8)
        @Pattern(
                regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,}$",
                message = "A senha deve conter ao menos uma letra e um número, e no mínimo 8 caracteres"
        )
        String senha) {}
