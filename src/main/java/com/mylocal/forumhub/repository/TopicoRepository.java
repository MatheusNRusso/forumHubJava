package com.mylocal.forumhub.repository;

import com.mylocal.forumhub.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Page<Topico> findAllByAtivoIsTrue(Pageable pageable);

    Page<Topico> findAllByAtivoIsFalse(Pageable pageable);

}
