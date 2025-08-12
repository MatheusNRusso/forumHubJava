package com.mylocal.forumhub.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TopicoShowDto(

        @NotNull
        Long id,

        String titulo,

        String mensagem,

        LocalDateTime dataCriacao

) {
}
