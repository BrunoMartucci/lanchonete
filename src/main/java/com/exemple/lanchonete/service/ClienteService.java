package com.exemple.lanchonete.service;

import com.exemple.lanchonete.dto.ValorTotalVendasDTO;
import com.exemple.lanchonete.dto.VendaDTO;
import com.exemple.lanchonete.entity.Cliente;
import com.exemple.lanchonete.entity.LogCredito;
import com.exemple.lanchonete.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

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

    public List<LogCredito> obterLogsCreditoCliente(Integer clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + clienteId));

        return cliente.getLogsCredito();
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
    public Page<VendaDTO> obterHistoricoVendasClientePaginado(Integer clienteId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return clienteRepository.obterHistoricoVendasClientePaginado(clienteId, pageable);
    }

    public Page<ValorTotalVendasDTO> obterValorTotalVendasClientePaginado(Integer clienteId, LocalDate startDate, LocalDate endDate, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return clienteRepository.obterValorTotalVendasClientePaginado(clienteId, startDate, endDate, pageable);
    }

}

