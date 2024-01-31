package com.exemple.lanchonete.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Producao extends PadraoIdInteiro{

    @ManyToOne
    @JoinColumn(name = "produto_final_id", nullable = false)
    private Produto produtoFinal;

    @Column(nullable = false)
    private Date dataProducao;

    public Produto getProdutoFinal() {
        return produtoFinal;
    }

    public void setProdutoFinal(Produto produtoFinal) {
        this.produtoFinal = produtoFinal;
    }

    public Date getDataProducao() {
        return dataProducao;
    }

    public void setDataProducao(Date dataProducao) {
        this.dataProducao = dataProducao;
    }
}

