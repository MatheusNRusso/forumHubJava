package com.mylocal.forumhub.controller;

import com.mylocal.forumhub.dto.CursoCreateDto;
import com.mylocal.forumhub.dto.CursoShowDto;
import com.mylocal.forumhub.service.CursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @PostMapping
    public CursoShowDto create(@RequestBody @Valid CursoCreateDto dto) {
        return cursoService.createCurso(dto);
    }

    @GetMapping
    public Page<CursoShowDto> getAll(Pageable pageable) {
        return cursoService.showAllCursos(pageable);
    }

    @GetMapping("/{id}")
    public CursoShowDto getById(@PathVariable Long id) {
        return cursoService.findByIdCurso(id);
    }

    @PutMapping("/{id}")
    public CursoShowDto update(@PathVariable Long id, @RequestBody @Valid CursoCreateDto dto) {
        return cursoService.updateCurso(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cursoService.deleteCurso(id);
    }
}
