package com.santam.designpatters.services.impl;

import com.santam.designpatters.domain.Cliente;
import com.santam.designpatters.domain.Endereco;
import com.santam.designpatters.repositories.ClienteRepository;
import com.santam.designpatters.repositories.EnderecoRepository;
import com.santam.designpatters.services.ClientService;
import com.santam.designpatters.services.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public List<Cliente> todos() {
        return repository.findAll();
    }

    @Override
    public Cliente buscarPorId(UUID id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Cliente inserir(Cliente cliente) {
        return salvarClienteComCep(cliente);
    }

    private Cliente salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            return enderecoRepository.save(novoEndereco);
        });

        cliente.setEndereco(endereco);

        return repository.save(cliente);
    }

    @Override
    public Cliente atualizar(UUID id, Cliente cliente) {
        repository.findById(id).orElseThrow();
        return salvarClienteComCep(cliente);
    }

    @Override
    public void deletar(UUID id) {
        repository.deleteById(id);
    }
}
