package com.mylocal.forumhub.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.mylocal.forumhub.dto.UsuarioShowDto;
import com.mylocal.forumhub.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);

    Page<Usuario> findAllByAtivoIsTrue(Pageable pageable);

    Page<Usuario> findAllByAtivoIsFalse(Pageable pageable);
}
