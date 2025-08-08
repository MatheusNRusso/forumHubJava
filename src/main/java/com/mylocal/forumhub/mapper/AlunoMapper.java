package com.mylocal.forumhub.mapper;

import com.mylocal.forumhub.dto.AlunoCreateDto;
import com.mylocal.forumhub.dto.AlunoShowDto;
import com.mylocal.forumhub.model.Aluno;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlunoMapper {
    Aluno toEntity(AlunoCreateDto dto);
    AlunoShowDto toDto(Aluno aluno);
}
