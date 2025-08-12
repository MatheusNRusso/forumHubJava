package com.mylocal.forumhub.dto;

public record RespostaShowDto(
        Long id,
        String mensagem,
        Long topicoId,
        Long autorId
) {
}
