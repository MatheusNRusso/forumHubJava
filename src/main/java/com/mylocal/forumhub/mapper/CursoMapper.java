package com.mylocal.forumhub.mapper;

import com.mylocal.forumhub.dto.CursoCreateDto;
import com.mylocal.forumhub.dto.CursoShowDto;
import com.mylocal.forumhub.model.Curso;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CursoMapper {
    CursoMapper INSTANCE = Mappers.getMapper(CursoMapper.class);

    Curso toEntity(CursoCreateDto cursoCreateDto);

    CursoShowDto toDto(Curso curso);
}
