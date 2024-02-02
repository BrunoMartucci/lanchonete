package com.exemple.lanchonete.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VendaDTO {

    private LocalDateTime dataVenda;
    private String produto;
    private BigDecimal valorTotal;

    public VendaDTO(LocalDateTime dataVenda, String produto, BigDecimal valorTotal) {
        this.dataVenda = dataVenda;
        this.produto = produto;
        this.valorTotal = valorTotal;
    }

}

