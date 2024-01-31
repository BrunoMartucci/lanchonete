package com.exemple.lanchonete.api;

import com.exemple.lanchonete.entity.Produto;
import com.exemple.lanchonete.service.ProducaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/producoes")
public class ProducaoController {

    @Autowired
    private ProducaoService producaoService;

    @PostMapping("/produzir/{produtoId}")
    public ResponseEntity<Void> produzirProduto(@PathVariable Integer produtoId) {
        try {
            producaoService.produzirProduto(produtoId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
