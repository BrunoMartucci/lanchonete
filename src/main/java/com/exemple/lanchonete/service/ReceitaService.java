package com.exemple.lanchonete.service;
import com.exemple.lanchonete.entity.Produto;
import com.exemple.lanchonete.entity.Receita;
import com.exemple.lanchonete.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    public List<Receita> listarReceitas() {
        return receitaRepository.findAll();
    }

    public Optional<Receita> obterReceitaPorId(Integer receitaId) {
        return receitaRepository.findById(receitaId);
    }

    public Receita salvarReceita(Receita receita) {
        return receitaRepository.save(receita);
    }

    public void deletarReceita(Integer receitaId) {
        receitaRepository.deleteById(receitaId);
    }

    public List<Receita> obterReceitasPorProdutoFinal(Produto produtoFinal) {
        return receitaRepository.findByProdutoFinal(produtoFinal);
    }
}
