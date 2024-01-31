package com.exemple.lanchonete.repository;

import com.exemple.lanchonete.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    boolean existsByNome(String nome);
}
