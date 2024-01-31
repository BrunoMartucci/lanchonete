package com.exemple.lanchonete.service;

import com.exemple.lanchonete.entity.Producao;
import com.exemple.lanchonete.entity.Produto;
import com.exemple.lanchonete.entity.Receita;
import com.exemple.lanchonete.repository.ProducaoRepository;
import com.exemple.lanchonete.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        // Obtém o produto final a ser produzido
        Produto produtoFinal = obterProdutoPorId(produtoId);

        // Verifica se há ingredientes suficientes
        for (Receita receita : receitaService.obterReceitasPorProdutoFinal(produtoFinal)) {
            Produto ingrediente = receita.getIngrediente();
            int quantidadeNecessaria = receita.getQuantidade();

            if (!estoqueService.temEstoque(ingrediente, quantidadeNecessaria)) {
                throw new RuntimeException("Estoque insuficiente para o ingrediente: " + ingrediente.getNomeProduto());
            }
        }

        // Realiza a produção
        for (Receita receita : receitaService.obterReceitasPorProdutoFinal(produtoFinal)) {
            Produto ingrediente = receita.getIngrediente();
            int quantidadeNecessaria = receita.getQuantidade();

            estoqueService.saidaEstoque(ingrediente, quantidadeNecessaria); // Saída do estoque de ingredientes
        }

        estoqueService.entradaEstoque(produtoFinal, 1); // Entrada no estoque do produto final

        // Registra a produção
        Producao producao = new Producao();
        producao.setProdutoFinal(produtoFinal);
        producao.setDataProducao(new Date());
        producaoRepository.save(producao);
    }
    public Produto obterProdutoPorId(Integer produtoId) {
        return produtoRepository.findById(produtoId)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o ID: " + produtoId));
    }

    // Outros métodos relacionados à produção...
}




