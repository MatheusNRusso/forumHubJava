package com.mylocal.forumhub.mapper;

import com.mylocal.forumhub.dto.UsuarioCreateDto;
import com.mylocal.forumhub.dto.UsuarioShowDto;
import com.mylocal.forumhub.model.Perfil;
import com.mylocal.forumhub.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioCreateDto usuarioCreateDto);

    UsuarioShowDto toDto(Usuario usuario);

    // Método para o MapStruct converter Long para Perfil
    default Perfil map(Long id) {
        if (id == null) {
            return null;
        }
        Perfil perfil = new Perfil();
        perfil.setId(id);
        return perfil;
    }
}
