package com.exemple.lanchonete.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente extends PadraoIdInteiro{

    @Column(nullable = false, unique = true, length = 60)
    private String nome;

    @Column(nullable = false)
    private BigDecimal creditos;

    @Column(nullable = false)
    private LocalDate dataDeCadastro = LocalDate.now();

    private List<LogCredito> logsCredito;

    public List<LogCredito> getLogsCredito() {
        return logsCredito;
    }

    public void setLogsCredito(List<LogCredito> logsCredito) {
        this.logsCredito = logsCredito;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getCreditos() {
        return creditos;
    }

    public void setCreditos(BigDecimal creditos) {
        this.creditos = creditos;
    }

    public LocalDate getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(LocalDate dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }
}

