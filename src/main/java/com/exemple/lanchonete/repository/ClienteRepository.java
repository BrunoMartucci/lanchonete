package com.exemple.lanchonete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository {
    boolean existsByNome(String nome);
}
