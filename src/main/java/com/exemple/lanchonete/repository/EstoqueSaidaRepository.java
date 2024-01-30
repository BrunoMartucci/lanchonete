package com.exemple.lanchonete.repository;

import com.exemple.lanchonete.entity.EstoqueSaida;
import com.exemple.lanchonete.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueSaidaRepository extends JpaRepository<EstoqueSaida, Long> {

    @Query("SELECT COALESCE(SUM(e.quantidade), 0) FROM EstoqueSaida e WHERE e.produto = :produto")
    int findQuantidadeSaidaByProduto(@Param("produto") Produto produto);

}
