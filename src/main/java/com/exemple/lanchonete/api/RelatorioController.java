package com.exemple.lanchonete.api;

import com.exemple.lanchonete.dto.LucroRelatorioDTO;
import com.exemple.lanchonete.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    private final RelatorioService relatorioService;

    @Autowired
    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("/lucro-total")
    public LucroRelatorioDTO obterLucroTotal(@RequestParam("startDate") LocalDate startDate,
                                             @RequestParam("endDate") LocalDate endDate) {
        return relatorioService.obterLucroTotal(startDate, endDate);
    }
}

