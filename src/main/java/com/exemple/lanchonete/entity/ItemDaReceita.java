package com.exemple.lanchonete.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ItemDaReceita extends PadraoIdInteiro{

        @ManyToOne
        @JoinColumn(name = "produto_final_id", nullable = false)
        private ProdutoFinal produtoFinal;
        @ManyToOne
        @JoinColumn(name = "ingrediente_id", nullable = false)
        private Produto ingrediente;

        @ManyToOne
        @JoinColumn(name = "receita_id", nullable = false)
        private Receita receita;

        @Column(nullable = false)
        private BigDecimal quantidade;
}




