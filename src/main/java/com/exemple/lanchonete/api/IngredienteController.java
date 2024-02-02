package com.exemple.lanchonete.api;

import com.exemple.lanchonete.entity.Ingrediente;
import com.exemple.lanchonete.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredientes")
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping
    public ResponseEntity<List<Ingrediente>> listarIngredientes() {
        List<Ingrediente> ingredientes = ingredienteService.listarIngredientes();
        return ResponseEntity.ok(ingredientes);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Ingrediente> cadastrarIngrediente(@RequestBody Ingrediente ingrediente) {
        Ingrediente novoIngrediente = ingredienteService.cadastrarIngrediente(ingrediente);
        return ResponseEntity.ok(novoIngrediente);
    }
}

