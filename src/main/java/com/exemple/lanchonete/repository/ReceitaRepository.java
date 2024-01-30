package com.exemple.lanchonete.repository;

import com.exemple.lanchonete.entity.Produto;
import com.exemple.lanchonete.entity.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    List<Receita> findByProdutoFinal(Produto produtoFinal);
}

