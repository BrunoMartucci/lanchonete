package com.exemple.lanchonete.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;  // Import LocalDate instead of java.util.Date

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Estoque extends PadraoIdInteiro {

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    @JsonBackReference
    private Produto produto;

    @Column(nullable = false)
    private BigDecimal quantidade;

    @Column(nullable = false)
    private LocalDate dataMovimentacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimentacaoEstoque tipoMovimentacaoEstoque;

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public void setDataMovimentacao(LocalDate dataMovimentacao) {  // Change the type to LocalDate
        this.dataMovimentacao = dataMovimentacao;
    }

    public void setTipoMovimentacaoEstoque(TipoMovimentacaoEstoque tipoMovimentacaoEstoque) {
        this.tipoMovimentacaoEstoque = tipoMovimentacaoEstoque;
    }
}
