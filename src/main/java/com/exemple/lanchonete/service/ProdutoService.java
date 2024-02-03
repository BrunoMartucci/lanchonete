package com.exemple.lanchonete.service;

import com.exemple.lanchonete.dto.ProdutoDTO;
import com.exemple.lanchonete.entity.*;
import com.exemple.lanchonete.repository.ProdutoRepository;
import com.exemple.lanchonete.repository.ReceitaRepository;
import com.exemple.lanchonete.transform.ProdutoDTOResultTransformer;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ReceitaService receitaService;

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

        TipoDoProduto tipoDoProduto = produto.getTipoDoProduto();
        if (tipoDoProduto == null) {
            throw new IllegalArgumentException("O tipo do produto não pode ser nulo");
        }

        Estoque estoque = new Estoque();
        estoque.setProduto(produto);
        estoque.setQuantidade(BigDecimal.ZERO);
        estoque.setDataMovimentacao(LocalDate.now());
        estoque.setTipoMovimentacaoEstoque(TipoMovimentacaoEstoque.ENTRADA);

        produto.setEstoque(List.of(estoque));

        return produtoRepository.save(produto);
    }

    public Produto atualizarProduto(Integer id, Produto produtoAtualizado) {
        Produto produto = obterProdutoPorId(id);

        produto.setNomeProduto(produtoAtualizado.getNomeProduto());
        produto.setValorDeEntrada(produtoAtualizado.getValorDeEntrada());
        produto.setValorDeVenda(produtoAtualizado.getValorDeVenda());
        produto.setTipoDoProduto(produtoAtualizado.getTipoDoProduto());
        produto.setSituacaoDoProduto(produtoAtualizado.getSituacaoDoProduto());

        return produtoRepository.save(produto);
    }

    public void deletarProduto(Integer id) {
        Produto produto = obterProdutoPorId(id);
        produtoRepository.delete(produto);
    }

    public Produto obterProdutoPorId(Integer produtoId) {
        return produtoRepository.findById(produtoId)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o ID: " + produtoId));
    }

    public void produzirProduto(Produto produtoFinal) {
        List<Receita> receitas = receitaService.obterReceitasPorProdutoFinal(produtoFinal);
        for (Receita receita : receitas) {
            Produto ingrediente = receita.getIngrediente();
            BigDecimal quantidadeNecessaria = receita.getQuantidade();

            if (!estoqueService.temEstoque(ingrediente, quantidadeNecessaria)) {
                throw new RuntimeException("Estoque insuficiente para o ingrediente: " + ingrediente.getNomeProduto());
            }
        }

        for (Receita receita : receitas) {
            Produto ingrediente = receita.getIngrediente();
            BigDecimal quantidadeNecessaria = receita.getQuantidade();

            estoqueService.saidaEstoque(ingrediente, quantidadeNecessaria);
        }

        estoqueService.entradaEstoque(produtoFinal, BigDecimal.valueOf(1));
    }

    @Autowired
    public void ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }
    public Page<ProdutoDTO> listarProdutosPaginados(Pageable pageable) {
        ResultTransformer transformer = new ProdutoDTOResultTransformer();
        return produtoRepository.listarProdutosPaginados(pageable, transformer);
    }


}
