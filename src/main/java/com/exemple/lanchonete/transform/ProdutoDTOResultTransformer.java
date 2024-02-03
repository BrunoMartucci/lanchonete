package com.exemple.lanchonete.transform;

import com.exemple.lanchonete.dto.ProdutoDTO;
import org.hibernate.transform.ResultTransformer;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoDTOResultTransformer implements ResultTransformer {

    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        for (int i = 0; i < tuple.length; i++) {
            switch (aliases[i]) {
                case "nome_produto":
                    produtoDTO.setNomeProduto((String) tuple[i]);
                    break;
                case "valor_de_entrada":
                    produtoDTO.setValorDeEntrada((BigDecimal) tuple[i]);
                    break;
                case "valor_de_venda":
                    produtoDTO.setValorDeVenda((BigDecimal) tuple[i]);
                    break;

            }
        }
        return produtoDTO;
    }


    @Override
    public List transformList(List collection) {
        return collection;
    }
}
