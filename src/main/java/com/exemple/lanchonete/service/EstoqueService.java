package com.exemple.lanchonete.service;

import com.exemple.lanchonete.entity.EstoqueEntrada;
import com.exemple.lanchonete.entity.EstoqueSaida;
import com.exemple.lanchonete.entity.Produto;
import com.exemple.lanchonete.repository.EstoqueEntradaRepository;
import com.exemple.lanchonete.repository.EstoqueSaidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueEntradaRepository estoqueEntradaRepository;

    @Autowired
    private EstoqueSaidaRepository estoqueSaidaRepository;

    // Verifica se há estoque suficiente para um produto
    public boolean temEstoque(Produto produto, int quantidadeRequerida) {
        int quantidadeEntrada = estoqueEntradaRepository.findQuantidadeEntradaByProduto(produto);
        int quantidadeSaida = estoqueSaidaRepository.findQuantidadeSaidaByProduto(produto);

        int quantidadeDisponivel = quantidadeEntrada - quantidadeSaida;

        return quantidadeDisponivel >= quantidadeRequerida;
    }

    // Obtém a quantidade disponível no estoque para um produto
    public int getQuantidadeDisponivel(Produto produto) {
        int quantidadeEntrada = estoqueEntradaRepository.findQuantidadeEntradaByProduto(produto);
        int quantidadeSaida = estoqueSaidaRepository.findQuantidadeSaidaByProduto(produto);

        return quantidadeEntrada - quantidadeSaida;
    }

    // Realiza uma entrada de estoque para um produto
    public void entradaEstoque(Produto produto, int quantidade) {
        EstoqueEntrada estoque = new EstoqueEntrada();
        estoque.setProduto(produto);
        estoque.setQuantidade(quantidade);
        estoque.setDataEntrada(new Date());
        estoqueEntradaRepository.save(estoque);
    }

    // Realiza uma saída de estoque para um produto
    public void saidaEstoque(Produto produto, int quantidade) {
        if (!temEstoque(produto, quantidade)) {
            throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNomeProduto());
        }

        EstoqueSaida estoque = new EstoqueSaida();
        estoque.setProduto(produto);
        estoque.setQuantidade((long) quantidade);
        estoque.setDataSaida(new Date());
        estoqueSaidaRepository.save(estoque);
    }

    // Implemente outros métodos conforme necessário
}

