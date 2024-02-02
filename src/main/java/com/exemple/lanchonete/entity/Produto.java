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
public abstract class Produto extends PadraoIdInteiro {

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
}
