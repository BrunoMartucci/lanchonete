package com.exemple.lanchonete.dto;

import java.math.BigDecimal;

public class RelatorioProdutoDTO {

    private String nomeProduto;
    private BigDecimal quantidadeTotal;
    private BigDecimal valorDeEntrada;
    private BigDecimal valorDeVenda;

    public RelatorioProdutoDTO(String nomeProduto, BigDecimal quantidadeTotal, BigDecimal valorDeEntrada, BigDecimal valorDeVenda) {
        this.nomeProduto = nomeProduto;
        this.quantidadeTotal = quantidadeTotal;
        this.valorDeEntrada = valorDeEntrada;
        this.valorDeVenda = valorDeVenda;
    }

    // getters e setters
}
