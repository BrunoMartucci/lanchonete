package com.exemple.lanchonete.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "produto_final_id")
public class ProdutoFinal extends Produto {

    @OneToMany(mappedBy = "produtoFinal", cascade = CascadeType.ALL)
    private List<ItemDaReceita> itensDaReceita;
}
