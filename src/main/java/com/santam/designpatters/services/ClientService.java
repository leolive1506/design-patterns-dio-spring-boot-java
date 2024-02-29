package com.santam.designpatters.services;

import com.santam.designpatters.domain.Cliente;

import java.util.List;
import java.util.UUID;

public interface ClientService {
    List<Cliente> todos();
    Cliente buscarPorId(UUID id);
    Cliente inserir(Cliente cliente);
    Cliente atualizar(UUID id, Cliente cliente);
    void deletar(UUID id);

}
