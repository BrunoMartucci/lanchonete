package com.exemple.lanchonete.service;

import com.exemple.lanchonete.entity.Ingrediente;
import com.exemple.lanchonete.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    public List<Ingrediente> listarIngredientes() {
        return ingredienteRepository.findAll();
    }

        public Ingrediente cadastrarIngrediente(Ingrediente ingrediente) {
            if (ingredienteRepository.existsByNomeProduto(ingrediente.getNomeProduto())) {
                throw new RuntimeException("Já existe um ingrediente com o mesmo nome: " + ingrediente.getNomeProduto());
            }
            return ingredienteRepository.save(ingrediente);
        }


}

