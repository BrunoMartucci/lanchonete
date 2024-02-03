package com.exemple.lanchonete.service;

import com.exemple.lanchonete.entity.Cliente;
import com.exemple.lanchonete.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class VendaService {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EstoqueService estoqueService;

    @Autowired
    private ProducaoService producaoService;

    public void realizarVenda(Integer clienteId, Produto produto, int quantidade) {
        Cliente cliente = clienteService.obterClientePorId(clienteId);

        BigDecimal valorTotal = produto.getValorDeVenda().multiply(BigDecimal.valueOf(quantidade));

        if (cliente.getCreditos().compareTo(valorTotal) < 0) {
            throw new RuntimeException("Créditos insuficientes para realizar a compra.");
        }


        if (!estoqueService.temEstoque(produto, BigDecimal.valueOf(quantidade))) {
            throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNomeProduto());
        }


        BigDecimal quantidadeBigDecimal = BigDecimal.valueOf(quantidade);
        estoqueService.saidaEstoque(produto, quantidadeBigDecimal);
        clienteService.adicionarCreditosAoCliente(clienteId, valorTotal.negate());

        BigDecimal custoEntrada = produto.getValorDeEntrada();
        producaoService.produzirProduto(produto.getId());
    }

}

