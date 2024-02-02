package com.exemple.lanchonete.service;

import com.exemple.lanchonete.dto.RelatorioProdutoDTO;
import com.exemple.lanchonete.repository.EstoqueRepository;
import com.exemple.lanchonete.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    private final ProdutoRepository produtoRepository;
    private final EstoqueRepository estoqueRepository;

    @Autowired
    public RelatorioService(ProdutoRepository produtoRepository, EstoqueRepository estoqueRepository) {
        this.produtoRepository = produtoRepository;
        this.estoqueRepository = estoqueRepository;
    }

    public List<RelatorioProdutoDTO> gerarRelatorioEntradaVenda(LocalDate startDate, LocalDate endDate) {
        List<Object[]> resultadoQuery = produtoRepository.obterRelatorioEntradaVenda(startDate, endDate);

        return resultadoQuery.stream()
                .map(obj -> new RelatorioProdutoDTO(
                        (String) obj[0],
                        (BigDecimal) obj[1],
                        (BigDecimal) obj[2],
                        (BigDecimal) obj[3]
                ))
                .collect(Collectors.toList());
    }
}
