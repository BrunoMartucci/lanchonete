package com.exemple.lanchonete.repository;

import com.exemple.lanchonete.entity.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
    boolean existsByNomeProduto(String nomeProduto);
}
