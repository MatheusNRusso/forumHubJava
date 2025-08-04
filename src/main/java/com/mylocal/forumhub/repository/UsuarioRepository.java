package com.mylocal.forumhub.repository;

import com.mylocal.forumhub.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByEmail(String email);

    Page<Usuario> findAllByAtivoIsTrue(Pageable pageable);

    Page<Usuario> findAllByAtivoIsFalse(Pageable pageable);
}
