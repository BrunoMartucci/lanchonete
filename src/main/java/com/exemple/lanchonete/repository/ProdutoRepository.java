package com.exemple.lanchonete.repository;

import com.exemple.lanchonete.dto.ProdutoDTO;
import com.exemple.lanchonete.entity.Produto;
import com.exemple.lanchonete.entity.TipoDoProduto;
import org.hibernate.transform.ResultTransformer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    boolean existsByNomeProduto(String nomeProduto);

    Optional<Produto> findByNomeProduto(String nomeProduto);

    // Busca produtos por tipo
    List<Produto> findByTipoDoProduto(TipoDoProduto tipoDoProduto);

    @Query(value = "SELECT nome_produto, valor_de_entrada, valor_de_venda, tipo_do_produto, situacao_do_produto, data_de_cadastro FROM produto",
            countQuery = "SELECT count(*) FROM produto",
            nativeQuery = true)
    Page<ProdutoDTO> listarProdutosPaginados(Pageable pageable, ResultTransformer transformer);

    @Query(value = "SELECT p.nome_produto AS nomeProduto, " +
            "SUM(e.quantidade) AS quantidadeTotal, " +
            "p.valor_de_entrada AS valorDeEntrada, " +
            "p.valor_de_venda AS valorDeVenda " +
            "FROM Produto p " +
            "JOIN Estoque e ON p.id = e.produto_id " +
            "WHERE e.tipo_movimentacao_estoque = 'ENTRADA' " +
            "AND e.data_movimentacao BETWEEN :startDate AND :endDate " +
            "GROUP BY p.nome_produto, p.valor_de_entrada, p.valor_de_venda", nativeQuery = true)
    List<Object[]> obterRelatorioEntradaVenda(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}



