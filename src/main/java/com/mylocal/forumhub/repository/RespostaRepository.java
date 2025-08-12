package com.mylocal.forumhub.repository;

import com.mylocal.forumhub.mapper.UsuarioMapper;
import com.mylocal.forumhub.model.Resposta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {

    Page<Resposta> findAllByAtivoIsTrue(Pageable pageable);

    Page<Resposta> findAllByAtivoIsFalse(Pageable pageable);




}
