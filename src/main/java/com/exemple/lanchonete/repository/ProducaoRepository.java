package com.exemple.lanchonete.repository;

import com.exemple.lanchonete.entity.Producao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducaoRepository extends JpaRepository <Producao, Integer> {
}
