package com.exemple.lanchonete.service;

import com.exemple.lanchonete.entity.Cliente;
import com.exemple.lanchonete.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente cadastrarCliente(Cliente cliente) {
        if (clienteRepository.existsByNome(cliente.getNome())) {
            throw new RuntimeException("Já existe um cliente com o mesmo nome: " + cliente.getNome());
        }

        cliente.setCreditos(BigDecimal.ZERO);

        return (Cliente) clienteRepository.save(cliente);
    }

    public Cliente atualizarCliente(Integer id, Cliente clienteAtualizado) {
        Cliente cliente = obterClientePorId(id);

        cliente.setNome(clienteAtualizado.getNome());
        cliente.setCreditos(clienteAtualizado.getCreditos());

        return (Cliente) clienteRepository.save(cliente);
    }

    public void deletarCliente(Integer id) {
        Cliente cliente = obterClientePorId(id);
        clienteRepository.delete(cliente);
    }



    public Cliente obterClientePorId(Integer clienteId) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(clienteId);

        if (clienteOptional.isPresent()) {
            return clienteOptional.get();
        } else {
            throw new EntityNotFoundException("Cliente não encontrado com o ID: " + clienteId);
        }
    }

    public void adicionarCreditosAoCliente(Integer clienteId, BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor dos créditos deve ser maior que zero");
        }

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com o ID: " + clienteId));

        cliente.setCreditos(cliente.getCreditos().add(valor));
        clienteRepository.save(cliente);
    }

}

