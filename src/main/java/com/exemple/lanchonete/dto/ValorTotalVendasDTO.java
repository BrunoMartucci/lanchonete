package com.exemple.lanchonete.dto;

import java.math.BigDecimal;

public class ValorTotalVendasDTO {

    private BigDecimal valorTotal;

    public ValorTotalVendasDTO(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    // setters (se necessário)
}
