package com.exemple.lanchonete.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
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
}
