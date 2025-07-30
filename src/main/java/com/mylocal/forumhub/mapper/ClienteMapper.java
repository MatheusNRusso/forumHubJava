package com.mylocal.forumhub.mapper;

import com.mylocal.forumhub.dto.ClienteCreateDto;
import com.mylocal.forumhub.dto.ClienteShowDto;
import com.mylocal.forumhub.model.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente toEntity(ClienteCreateDto dto);
    ClienteShowDto toDto(Cliente cliente);
}
