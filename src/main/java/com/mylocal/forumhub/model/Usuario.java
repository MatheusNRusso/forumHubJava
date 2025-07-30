package com.mylocal.forumhub.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity(name = "Usuario")
@Table(name = "usuario")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 64)
    private String email;

    @Column(nullable = false, length = 128)
    private String senha;

    @Column(nullable = false)
    private boolean ativo;

    @CreationTimestamp
    @Column(name = "data_criacao", updatable = false )
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    public void ativarUsuario() {
        this.ativo = true;
    }

    public void inativarUsuario() {
        this.ativo = false;
    }
}
