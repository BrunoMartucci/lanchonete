package com.exemple.lanchonete.api;

import com.exemple.lanchonete.entity.Receita;
import com.exemple.lanchonete.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @GetMapping
    public ResponseEntity<List<Receita>> listarReceitas() {
        List<Receita> receitas = receitaService.listarReceitas();
        return ResponseEntity.ok(receitas);
    }

    @GetMapping("/{receitaId}")
    public ResponseEntity<Receita> obterReceitaPorId(@PathVariable Integer receitaId) {
        Optional<Receita> receita = receitaService.obterReceitaPorId(receitaId);
        return receita.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/salvar")
    public ResponseEntity<Receita> salvarReceita(@RequestBody Receita receita) {
        try {
            Receita novaReceita = receitaService.salvarReceita(receita);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaReceita);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{receitaId}")
    public ResponseEntity<Void> deletarReceita(@PathVariable Integer receitaId) {
        try {
            receitaService.deletarReceita(receitaId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
