package com.exemple.lanchonete.service;

import com.exemple.lanchonete.entity.Estoque;
import com.exemple.lanchonete.entity.Produto;
import com.exemple.lanchonete.entity.TipoMovimentacaoEstoque;
import com.exemple.lanchonete.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    public boolean temEstoque(Produto produto, BigDecimal quantidadeRequerida) {
        int quantidadeMovimentacao = estoqueRepository.findQuantidadeMovimentacaoByProduto(produto);
        BigDecimal quantidadeMovimentacaoBigDecimal = BigDecimal.valueOf(quantidadeMovimentacao);

        return quantidadeMovimentacaoBigDecimal.compareTo(quantidadeRequerida) >= 0;
    }

    public int getQuantidadeDisponivel(Produto produto) {
        int quantidadeMovimentacao = estoqueRepository.findQuantidadeMovimentacaoByProduto(produto);

        return quantidadeMovimentacao;
    }

    public void entradaEstoque(Produto produto, BigDecimal quantidade) {
        Estoque estoque = new Estoque();
        estoque.setProduto(produto);
        estoque.setQuantidade(quantidade);
        estoque.setDataMovimentacao(LocalDate.now());
        estoque.setTipoMovimentacaoEstoque(TipoMovimentacaoEstoque.ENTRADA);
        estoqueRepository.save(estoque);
    }

    public void saidaEstoque(Produto produto, BigDecimal quantidade) {
        if (!temEstoque(produto, quantidade)) {
            throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNomeProduto());
        }

        Estoque estoque = new Estoque();
        estoque.setProduto(produto);
        estoque.setQuantidade(quantidade);
        estoque.setDataMovimentacao(LocalDate.now());
        estoque.setTipoMovimentacaoEstoque(TipoMovimentacaoEstoque.SAIDA);
        estoqueRepository.save(estoque);
    }
}
