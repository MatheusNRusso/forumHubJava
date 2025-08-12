package com.mylocal.forumhub.controller;

import com.mylocal.forumhub.dto.CursoCreateDto;
import com.mylocal.forumhub.dto.CursoShowDto;
import com.mylocal.forumhub.service.CursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @PostMapping
    public ResponseEntity<CursoShowDto> create(@RequestBody @Valid CursoCreateDto dto, UriComponentsBuilder uriBuilder) {


            var curso = cursoService.createCurso(dto);
            var uri = uriBuilder.path("/cursos/{id}")
                    .buildAndExpand(curso.id())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(curso);


    }

    @GetMapping
    public ResponseEntity<Page<CursoShowDto>> getAll(@PageableDefault(size=10,sort="nome") Pageable pageable) {
        return ResponseEntity.ok(cursoService.showAllCursos(pageable));
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
