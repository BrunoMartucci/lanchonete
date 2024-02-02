package com.exemple.lanchonete.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Receita extends PadraoIdInteiro {

    @ManyToOne
    @JoinColumn(name = "produto_final_id", nullable = false)
    private Produto produtoFinal;

    @OneToMany(mappedBy = "receita", cascade = CascadeType.ALL)
    private List<ItemDaReceita> itensDaReceita;

    public List<ItemDaReceita> getItensDaReceita() {
        return itensDaReceita;
    }

    public void setItensDaReceita(List<ItemDaReceita> itensDaReceita) {
        this.itensDaReceita = itensDaReceita;
    }
    @ManyToOne
    @JoinColumn(name = "ingrediente_id", nullable = false)
    private Produto ingrediente;

    @Column(nullable = false)
    private BigDecimal quantidade;

    public Produto getProdutoFinal() {
        return produtoFinal;
    }

    public Produto getIngrediente() {
        return ingrediente;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setProdutoFinal(Produto produtoFinal) {
        this.produtoFinal = produtoFinal;
    }

    public void setIngrediente(Produto ingrediente) {
        this.ingrediente = ingrediente;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

}
