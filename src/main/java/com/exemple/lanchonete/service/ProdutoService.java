package com.exemple.lanchonete.service;

import com.exemple.lanchonete.entity.Produto;
import com.exemple.lanchonete.entity.Receita;
import com.exemple.lanchonete.entity.TipoDoProduto;
import com.exemple.lanchonete.repository.ProdutoRepository;
import com.exemple.lanchonete.repository.ReceitaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private EstoqueService estoqueService;

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto cadastrarProduto(Produto produto) {
        if (produtoRepository.existsByNomeProduto(produto.getNomeProduto())) {
            throw new RuntimeException("Já existe um produto com o mesmo nome: " + produto.getNomeProduto());
        }

        // Lógica para inicializar o estoque, se necessário
        produto.setEntradasEstoque(null); // ajuste conforme a estrutura do seu modelo
        produto.setSaidasEstoque(null);   // ajuste conforme a estrutura do seu modelo

        // Verificar se o tipo do produto é válido (final, matéria-prima, adicional)
        TipoDoProduto tipoDoProduto = produto.getTipoDoProduto();
        if (tipoDoProduto == null) {
            throw new IllegalArgumentException("O tipo do produto não pode ser nulo");
        }

        // Outras verificações específicas do tipo, se necessário...

        return produtoRepository.save(produto);
    }

    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        Produto produto = obterProdutoPorId(id);

        // Lógica para atualizar outros campos, se necessário
        produto.setNomeProduto(produtoAtualizado.getNomeProduto());
        produto.setValorDeEntrada(produtoAtualizado.getValorDeEntrada());
        produto.setValorDeVenda(produtoAtualizado.getValorDeVenda());
        produto.setTipoDoProduto(produtoAtualizado.getTipoDoProduto());
        produto.setSituacaoDoProduto(produtoAtualizado.getSituacaoDoProduto());

        return produtoRepository.save(produto);
    }

    public void deletarProduto(Long id) {
        Produto produto = obterProdutoPorId(id);
        produtoRepository.delete(produto);
    }

    public Produto obterProdutoPorId(Long produtoId) {
        return produtoRepository.findById(produtoId)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o ID: " + produtoId));
    }

    public void produzirProduto(Produto produtoFinal) {
        // Verifica se há ingredientes suficientes
        for (Receita receita : produtoFinal.getReceitas()) {
            Produto ingrediente = receita.getIngrediente();
            int quantidadeNecessaria = Math.toIntExact(receita.getQuantidade());

            if (!estoqueService.temEstoque(ingrediente, quantidadeNecessaria)) {
                throw new RuntimeException("Estoque insuficiente para o ingrediente: " + ingrediente.getNomeProduto());
            }
        }

        // Realiza a produção
        for (Receita receita : produtoFinal.getReceitas()) {
            Produto ingrediente = receita.getIngrediente();
            int quantidadeNecessaria = Math.toIntExact(receita.getQuantidade());

            estoqueService.saidaEstoque(ingrediente, quantidadeNecessaria); // Saída do estoque de ingredientes
        }

        estoqueService.entradaEstoque(produtoFinal, 1); // Entrada no estoque do produto final
    }
}