package com.exemple.lanchonete.service;

import com.exemple.lanchonete.entity.Estoque;
import com.exemple.lanchonete.entity.Produto;
import com.exemple.lanchonete.entity.TipoMovimentacaoEstoque;
import com.exemple.lanchonete.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    // Verifica se há estoque suficiente para um produto
    public boolean temEstoque(Produto produto, int quantidadeRequerida) {
        int quantidadeMovimentacao = estoqueRepository.findQuantidadeMovimentacaoByProduto(produto);

        return quantidadeMovimentacao >= quantidadeRequerida;
    }

    // Obtém a quantidade disponível no estoque para um produto
    public int getQuantidadeDisponivel(Produto produto) {
        int quantidadeMovimentacao = estoqueRepository.findQuantidadeMovimentacaoByProduto(produto);

        return quantidadeMovimentacao;
    }

    // Realiza uma entrada de estoque para um produto
    public void entradaEstoque(Produto produto, int quantidade) {
        Estoque estoque = new Estoque();
        estoque.setProduto(produto);
        estoque.setQuantidade((long) quantidade);
        estoque.setDataMovimentacao(LocalDate.now());
        estoque.setTipoMovimentacaoEstoque(TipoMovimentacaoEstoque.ENTRADA);
        estoqueRepository.save(estoque);
    }

    // Realiza uma saída de estoque para um produto
    public void saidaEstoque(Produto produto, int quantidade) {
        if (!temEstoque(produto, quantidade)) {
            throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNomeProduto());
        }

        Estoque estoque = new Estoque();
        estoque.setProduto(produto);
        estoque.setQuantidade((long) quantidade);
        estoque.setDataMovimentacao(LocalDate.now());
        estoque.setTipoMovimentacaoEstoque(TipoMovimentacaoEstoque.SAIDA);
        estoqueRepository.save(estoque);
    }

    // Implemente outros métodos conforme necessário
}
