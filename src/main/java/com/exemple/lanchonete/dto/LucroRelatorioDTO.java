package com.exemple.lanchonete.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class LucroRelatorioDTO {

    private BigDecimal lucroTotal;

    public LucroRelatorioDTO(BigDecimal lucroTotal) {
        this.lucroTotal = lucroTotal;
    }
}
