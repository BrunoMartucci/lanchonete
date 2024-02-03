package com.exemple.lanchonete.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Venda extends PadraoIdInteiro{

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @Column(nullable = false)
    private BigDecimal custoTotal;

    @Column(nullable = false)
    private LocalDate dataVenda;
}

