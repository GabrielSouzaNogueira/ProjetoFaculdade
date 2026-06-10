package com.example.projetoFacul.DTOS.Usuario;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UpdateUserDTO(

        @JsonProperty("nome")
        String nome,

        @JsonProperty("senha")
        String senha
) {
}
