package com.exemple.lanchonete.repository;

import com.exemple.lanchonete.entity.EstoqueEntrada;
import com.exemple.lanchonete.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueEntradaRepository extends JpaRepository<EstoqueEntrada, Long> {

    @Query("SELECT COALESCE(SUM(e.quantidade), 0) FROM EstoqueEntrada e WHERE e.produto = :produto")
    int findQuantidadeEntradaByProduto(@Param("produto") Produto produto);

}