package com.mylocal.forumhub.service;

import com.mylocal.forumhub.dto.PerfilCreateDto;
import com.mylocal.forumhub.dto.PerfilShowDto;
import com.mylocal.forumhub.mapper.PerfilMapper;
import com.mylocal.forumhub.repository.PerfilRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfilService {

    private final PerfilRepository perfilRepository;
    private final PerfilMapper perfilMapper;

    public PerfilShowDto create(@Valid PerfilCreateDto dto) {
        var perfil = perfilMapper.toEntity(dto);
        var perfilSalvo = perfilRepository.save(perfil);
        return perfilMapper.toDto(perfilSalvo);
    }

    public PerfilShowDto findById(Long id) {
        return perfilRepository.findById(id)
                .map(perfilMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));
    }

    public Page<PerfilShowDto> findAll(Pageable pageable) {
        return perfilRepository.findAll(pageable)
                .map(perfilMapper::toDto);
    }

    public PerfilShowDto update(Long id, @Valid PerfilCreateDto dto) {
        var perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));

        perfil.setNome(dto.nome());
        perfil.setNivel(dto.nivel());

        var perfilAtualizado = perfilRepository.save(perfil);
        return perfilMapper.toDto(perfilAtualizado);
    }

    public void delete(Long id) {
        if (!perfilRepository.existsById(id)) {
            throw new RuntimeException("Perfil não encontrado");
        }
        perfilRepository.deleteById(id);
    }
}
