package com.exemple.lanchonete.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Produto extends PadraoIdInteiro{

    @Column(nullable = false, unique = true, length = 60)
    private String nomeProduto;

    @Column(nullable = false)
    private BigDecimal valorDeEntrada;

    @Column(nullable = false)
    private BigDecimal valorDeVenda;

    @Enumerated(EnumType.STRING)
    private TipoDoProduto tipoDoProduto;

    @Enumerated(EnumType.STRING)
    private SituacaoDoProduto situacaoDoProduto;

    @Column(nullable = false)
    private LocalDate dataDeCadastro = LocalDate.now();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<EstoqueEntrada> entradasEstoque;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<EstoqueSaida> saidasEstoque;

    @OneToMany(mappedBy = "produtoFinal", cascade = CascadeType.ALL)
    private List<Receita> receitas;

    @OneToMany(mappedBy = "ingrediente", cascade = CascadeType.ALL)
    private List<Receita> receitasComIngrediente;


    public String getNomeProduto() {
        return nomeProduto;
    }

    public BigDecimal getValorDeEntrada() {
        return valorDeEntrada;
    }

    public BigDecimal getValorDeVenda() {
        return valorDeVenda;
    }

    public TipoDoProduto getTipoDoProduto() {
        return tipoDoProduto;
    }

    public SituacaoDoProduto getSituacaoDoProduto() {
        return situacaoDoProduto;
    }

    public LocalDate getDataDeCadastro() {
        return dataDeCadastro;
    }

    public List<EstoqueEntrada> getEntradasEstoque() {
        return entradasEstoque;
    }

    public List<EstoqueSaida> getSaidasEstoque() {
        return saidasEstoque;
    }

    public List<Receita> getReceitas() {
        return receitas;
    }

    public List<Receita> getReceitasComIngrediente() {
        return receitasComIngrediente;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setValorDeEntrada(BigDecimal valorDeEntrada) {
        this.valorDeEntrada = valorDeEntrada;
    }

    public void setValorDeVenda(BigDecimal valorDeVenda) {
        this.valorDeVenda = valorDeVenda;
    }

    public void setTipoDoProduto(TipoDoProduto tipoDoProduto) {
        this.tipoDoProduto = tipoDoProduto;
    }

    public void setSituacaoDoProduto(SituacaoDoProduto situacaoDoProduto) {
        this.situacaoDoProduto = situacaoDoProduto;
    }

    public void setDataDeCadastro(LocalDate dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

    public void setEntradasEstoque(List<EstoqueEntrada> entradasEstoque) {
        this.entradasEstoque = entradasEstoque;
    }

    public void setSaidasEstoque(List<EstoqueSaida> saidasEstoque) {
        this.saidasEstoque = saidasEstoque;
    }

    public void setReceitas(List<Receita> receitas) {
        this.receitas = receitas;
    }

    public void setReceitasComIngrediente(List<Receita> receitasComIngrediente) {
        this.receitasComIngrediente = receitasComIngrediente;
    }
}
