package com.exemple.lanchonete.repository;

import com.exemple.lanchonete.entity.Estoque;
import com.exemple.lanchonete.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface EstoqueRepository extends JpaRepository <Estoque, Integer> {

    @Query("SELECT COALESCE(SUM(e.quantidade), 0) FROM Estoque e WHERE e.produto = :produto")
    int findQuantidadeMovimentacaoByProduto(@Param("produto") Produto produto);

    @Query("SELECT COALESCE(SUM(e.quantidade), 0) FROM Estoque e WHERE e.produto = :produto AND e.tipoMovimentacaoEstoque = 'SAIDA'")
    int findQuantidadeSaidaByProduto(@Param("produto") Produto produto);

}







