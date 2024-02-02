package com.exemple.lanchonete.dto;

import com.exemple.lanchonete.entity.Produto;
import jakarta.persistence.Tuple;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProdutoDTO {
    private String nomeProduto;
    private BigDecimal valorDeEntrada;
    private BigDecimal valorDeVenda;
    private String tipoDoProduto;
    private String situacaoDoProduto;
    private LocalDate dataDeCadastro;

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setValorDeEntrada(BigDecimal valorDeEntrada) {
        this.valorDeEntrada = valorDeEntrada;
    }

    public void setValorDeVenda(BigDecimal valorDeVenda) {
        this.valorDeVenda = valorDeVenda;
    }

    public void setTipoDoProduto(String tipoDoProduto) {
        this.tipoDoProduto = tipoDoProduto;
    }

    public void setSituacaoDoProduto(String situacaoDoProduto) {
        this.situacaoDoProduto = situacaoDoProduto;
    }

    public void setDataDeCadastro(LocalDate dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

    public ProdutoDTO() {
    }
    public ProdutoDTO(Produto produto) {
        this.nomeProduto = produto.getNomeProduto();
        this.valorDeEntrada = produto.getValorDeEntrada();
        this.valorDeVenda = produto.getValorDeVenda();
        this.tipoDoProduto = produto.getTipoDoProduto().toString();
        this.situacaoDoProduto = produto.getSituacaoDoProduto().toString();
        this.dataDeCadastro = produto.getDataDeCadastro();
    }

    public ProdutoDTO(Tuple tuple) {
        this.nomeProduto = (String) tuple.get("nome_produto");
        this.valorDeEntrada = (BigDecimal) tuple.get("valor_de_entrada");
        this.valorDeVenda = (BigDecimal) tuple.get("valor_de_venda");
        this.tipoDoProduto = (String) tuple.get("tipo_do_produto");
        this.situacaoDoProduto = (String) tuple.get("situacao_do_produto");
        this.dataDeCadastro = (LocalDate) tuple.get("data_de_cadastro");
    }
}
