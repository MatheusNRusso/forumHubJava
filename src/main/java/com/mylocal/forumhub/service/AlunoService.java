package com.mylocal.forumhub.service;

import com.mylocal.forumhub.dto.ClienteCreateDto;
import com.mylocal.forumhub.dto.ClienteShowDto;
import com.mylocal.forumhub.dto.UsuarioShowDto;
import com.mylocal.forumhub.mapper.ClienteMapper;
import com.mylocal.forumhub.model.Cliente;
import com.mylocal.forumhub.repository.ClienteRepository;
import com.mylocal.forumhub.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
                .ativo(true)
                .build();
        var clienteNovo =  clienteRepository.save(cliente);
        return clienteMapper.toDto(clienteNovo);
    }

    public Page<ClienteShowDto> listarClientes(Pageable pageable) {
        return clienteRepository
                .findAll(pageable)
                .map(clienteMapper::toDto);
    }

    public ClienteShowDto buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .map(clienteMapper::toDto).orElse(null);
    }
    public Page<ClienteShowDto> listarClientesAtivos(Pageable pageable) {
        return clienteRepository.findAllByAtivoIsTrue(pageable).map(clienteMapper::toDto);
    }

    public Page<ClienteShowDto> listarClientesInativos(Pageable pageable) {
        return clienteRepository.findAllByAtivoIsFalse(pageable).map(clienteMapper::toDto);
    }

    public void desativarCliente(Long id) {
        var cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setAtivo(false);
        clienteRepository.save(cliente);
    }

    public void ativarCliente(Long id) {
        var cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setAtivo(true);
        clienteRepository.save(cliente);
    }




}
