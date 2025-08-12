package com.mylocal.forumhub.mapper;


import com.mylocal.forumhub.dto.TopicoCreateDto;
import com.mylocal.forumhub.dto.TopicoDetailDto;
import com.mylocal.forumhub.dto.TopicoShowDto;
import com.mylocal.forumhub.model.Topico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TopicoMapper {

    Topico toEntity(TopicoCreateDto topicoCreateDto);

    TopicoShowDto toDto(Topico topico);

    @Mapping(source = "autor.nome", target = "nomeAutor")
    TopicoDetailDto toDetailDto(Topico topico);

}