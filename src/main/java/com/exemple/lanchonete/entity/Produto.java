package com.exemple.lanchonete.entity;

import com.exemple.lanchonete.entity.PadraoIdInteiro;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Produto extends PadraoIdInteiro {

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
    private List<Estoque> estoque;

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

    public List<Estoque> getEstoque() {
        return estoque;
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

    public void setEstoque(List<Estoque> estoque) {
        this.estoque = estoque;
    }
}
