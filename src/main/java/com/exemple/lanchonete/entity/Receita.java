package com.exemple.lanchonete.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Receita extends PadraoIdInteiro{

    @ManyToOne
    @JoinColumn(name = "produto_final_id", nullable = false)
    private Produto produtoFinal;

    @ManyToOne
    @JoinColumn(name = "ingrediente_id", nullable = false)
    private Produto ingrediente;

    @Column(nullable = false)
    private Long quantidade;

    public Produto getProdutoFinal() {
        return produtoFinal;
    }

    public Produto getIngrediente() {
        return ingrediente;
    }

    public Long getQuantidade() {
        return quantidade;
    }
}
