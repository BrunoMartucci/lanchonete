package com.exemple.lanchonete.repository;

import com.exemple.lanchonete.dto.LucroRelatorioDTO;
import com.exemple.lanchonete.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
    @Query(value = "SELECT COALESCE(SUM(v.valor_total - v.custo_total), 0) AS lucro_total " +
            "FROM venda v " +
            "WHERE v.data_venda BETWEEN :startDate AND :endDate", nativeQuery = true)
    LucroRelatorioDTO obterLucroTotal(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
