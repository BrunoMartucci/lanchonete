package com.exemple.lanchonete.dto;

import lombok.Setter;

import java.math.BigDecimal;

@Setter
public class ValorTotalVendasDTO {

    private BigDecimal valorTotal;

    public ValorTotalVendasDTO(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

}
