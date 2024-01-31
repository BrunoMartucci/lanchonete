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
    public ResponseEntity<String> produzirProduto(@PathVariable Integer produtoId) {
        try {
            Produto produtoFinal = producaoService.obterProdutoPorId(produtoId);
            producaoService.produzirProduto(produtoFinal);

            // Se você quiser rastrear as produções, crie uma entidade Producao e persista no repositório ProducaoRepository

            return ResponseEntity.ok("Produção realizada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro durante a produção: " + e.getMessage());
        }
    }

    // Outros métodos relacionados à produção...
}
