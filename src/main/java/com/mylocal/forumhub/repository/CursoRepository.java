package com.mylocal.forumhub.repository;

import com.mylocal.forumhub.model.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    boolean existsByNome(String nome);

//    Page<Curso> findAllByAtivoIsTrue(Pageable pageable);
}
