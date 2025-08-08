package com.mylocal.forumhub.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.mylocal.forumhub.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Page<Cliente> findAllByAtivoIsTrue(Pageable pageable);

    Page<Cliente> findAllByAtivoIsFalse(Pageable pageable);
}
