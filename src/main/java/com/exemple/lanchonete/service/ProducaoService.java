package com.exemple.lanchonete.service;

import com.exemple.lanchonete.dto.ProdutoDTO;
import com.exemple.lanchonete.entity.Producao;
import com.exemple.lanchonete.entity.Produto;
import com.exemple.lanchonete.entity.Receita;
import com.exemple.lanchonete.repository.ProducaoRepository;
import com.exemple.lanchonete.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class ProducaoService {

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private EstoqueService estoqueService;

    @Autowired
    private ProducaoRepository producaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;


    public void produzirProduto(Integer produtoId) {
        Produto produtoFinal = obterProdutoPorId(produtoId);

        for (Receita receita : receitaService.obterReceitasPorProdutoFinal(produtoFinal)) {
            Produto ingrediente = receita.getIngrediente();
            BigDecimal quantidadeNecessaria = receita.getQuantidade();

            if (!estoqueService.temEstoque(ingrediente, BigDecimal.valueOf(quantidadeNecessaria.intValue()))) {
                throw new RuntimeException("Estoque insuficiente para o ingrediente: " + ingrediente.getNomeProduto());
            }
        }

        for (Receita receita : receitaService.obterReceitasPorProdutoFinal(produtoFinal)) {
            Produto ingrediente = receita.getIngrediente();
            BigDecimal quantidadeNecessaria = receita.getQuantidade();

            estoqueService.saidaEstoque(ingrediente, BigDecimal.valueOf(quantidadeNecessaria.intValue()));
        }

        estoqueService.entradaEstoque(produtoFinal, BigDecimal.ONE);

        Producao producao = new Producao();
        producao.setProdutoFinal(produtoFinal);
        producao.setDataProducao(new Date());
        producaoRepository.save(producao);
    }
    public Produto obterProdutoPorId(Integer produtoId) {
        return produtoRepository.findById(produtoId)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o ID: " + produtoId));
    }
}





