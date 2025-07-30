package com.mylocal.forumhub.service;

import com.mylocal.forumhub.dto.ClienteCreateDto;
import com.mylocal.forumhub.dto.ClienteShowDto;
import com.mylocal.forumhub.mapper.ClienteMapper;
import com.mylocal.forumhub.model.Cliente;
import com.mylocal.forumhub.repository.ClienteRepository;
import com.mylocal.forumhub.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {


    private final ClienteRepository clienteRepository;

    private final  ClienteMapper clienteMapper;

    private final UsuarioRepository usuarioRepository;

    public ClienteShowDto cadastrar(ClienteCreateDto dto) {
        var usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


        var cliente = Cliente.builder()
                .nome(dto.nome())
                .telefone(dto.telefone())
                .cpf(dto.cpf())
                .usuario(usuario)
                .build();

        var clienteNovo =  clienteRepository.save(cliente);
        return clienteMapper.toDto(clienteNovo);
    }
}
