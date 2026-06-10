package com.example.projetoFacul.DTOS.Produto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProdDTO(


        @JsonProperty("descProduto")
        String descProduto,

        @JsonProperty("codBarra")
        String codBarra,

        @JsonProperty("precoCusto")
        BigDecimal precoCusto,

        @JsonProperty("precoVenda")
        BigDecimal precoVenda,

        @JsonProperty("quantidade")
        int quantidade
) {
}
