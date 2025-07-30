package com.mylocal.forumhub.mapper;

import com.mylocal.forumhub.dto.UsuarioCreateDto;
import com.mylocal.forumhub.dto.UsuarioShowDto;
import com.mylocal.forumhub.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioCreateDto usuarioCreateDto);

    UsuarioShowDto toDto(Usuario usuario);
}
