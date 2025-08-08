package com.mylocal.forumhub.service;

import com.mylocal.forumhub.dto.CursoCreateDto;
import com.mylocal.forumhub.dto.CursoShowDto;
import com.mylocal.forumhub.mapper.CursoMapper;
import com.mylocal.forumhub.model.Curso;
import com.mylocal.forumhub.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;

    public CursoShowDto createCurso(CursoCreateDto dto) {
        var curso = cursoMapper.toEntity(dto);
        var cursoSalvo = cursoRepository.save(curso);
        return cursoMapper.toDto(cursoSalvo);

    }

    public Page<CursoShowDto> showAllCursos(Pageable pageable) {
        return cursoRepository.findAll(pageable).map(cursoMapper::toDto);
    }

    public CursoShowDto findByIdCurso(Long id) {
        return cursoRepository.findById(id)
                .map(cursoMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
    }

    public CursoShowDto updateCurso(Long id, CursoCreateDto dto) {
        var curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        curso.setNome(dto.nome());
        curso.setCategoria(dto.categoria());

        var cursoAtualizado = cursoRepository.save(curso);
        return cursoMapper.toDto(cursoAtualizado);
    }

    public void deleteCurso(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new RuntimeException("Curso não encontrado");
        }
        cursoRepository.deleteById(id);
    }
}
