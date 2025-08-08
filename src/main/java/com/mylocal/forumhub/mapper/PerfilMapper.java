package com.mylocal.forumhub.mapper;

import com.mylocal.forumhub.dto.PerfilCreateDto;
import com.mylocal.forumhub.dto.PerfilShowDto;
import com.mylocal.forumhub.model.Perfil;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PerfilMapper {
    Perfil toEntity(PerfilCreateDto perfilCreateDto);

    PerfilShowDto toDto(Perfil perfil);
}
