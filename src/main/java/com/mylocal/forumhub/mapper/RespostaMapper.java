package com.mylocal.forumhub.mapper;

import com.mylocal.forumhub.dto.RespostaCreateDto;
import com.mylocal.forumhub.dto.RespostaShowDto;
import com.mylocal.forumhub.model.Resposta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RespostaMapper {

    Resposta toEntity(RespostaCreateDto respostaCreateDto);

    @Mapping(source = "autor.id", target = "autorId")
    @Mapping(source = "topico.id", target = "topicoId")
    RespostaShowDto toDto(Resposta resposta);
}
