package com.exemple.lanchonete.repository;

import com.exemple.lanchonete.entity.Produto;
import com.exemple.lanchonete.entity.TipoDoProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    boolean existsByNomeProduto(String nomeProduto);

    Optional<Produto> findByNomeProduto(String nomeProduto);

    // Busca produtos por tipo
    List<Produto> findByTipoDoProduto(TipoDoProduto tipoDoProduto);

}

