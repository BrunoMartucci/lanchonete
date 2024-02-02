package com.exemple.lanchonete.repository;

import com.exemple.lanchonete.dto.ValorTotalVendasDTO;
import com.exemple.lanchonete.dto.VendaDTO;
import com.exemple.lanchonete.entity.Cliente;
import com.exemple.lanchonete.entity.LogCredito;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    boolean existsByNome(String nome);
    List<LogCredito> findByClienteIdOrderByDataRegistroDesc(Integer clienteId);

    @Query(value = "SELECT v.data_venda AS dataVenda, p.nome_produto AS produto, v.valor_total AS valorTotal " +
            "FROM venda v " +
            "JOIN cliente c ON v.cliente_id = c.id " +
            "JOIN item_venda iv ON v.id = iv.venda_id " +
            "JOIN produto p ON iv.produto_id = p.id " +
            "WHERE c.id = :clienteId " +
            "ORDER BY v.data_venda DESC",
            countQuery = "SELECT COUNT(v.id) FROM venda v JOIN cliente c ON v.cliente_id = c.id WHERE c.id = :clienteId",
            nativeQuery = true)
    Page<VendaDTO> obterHistoricoVendasClientePaginado(@Param("clienteId") Integer clienteId, Pageable pageable);

    @Query(value = "SELECT COALESCE(SUM(v.valor_total), 0) AS valorTotal " +
            "FROM venda v " +
            "JOIN cliente c ON v.cliente_id = c.id " +
            "WHERE c.id = :clienteId " +
            "AND v.data_venda BETWEEN :startDate AND :endDate",
            countQuery = "SELECT COUNT(DISTINCT v.id) FROM venda v JOIN cliente c ON v.cliente_id = c.id WHERE c.id = :clienteId AND v.data_venda BETWEEN :startDate AND :endDate",
            nativeQuery = true)
    Page<ValorTotalVendasDTO> obterValorTotalVendasClientePaginado(@Param("clienteId") Integer clienteId,
                                                                   @Param("startDate") LocalDate startDate,
                                                                   @Param("endDate") LocalDate endDate,
                                                                   Pageable pageable);
}



