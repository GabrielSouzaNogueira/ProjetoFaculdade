package com.example.projetoFacul.Exception.ExceptionProd;

public class StatusProdNotFound extends RuntimeException{

    public StatusProdNotFound(String mensagem) {
        super(mensagem);
    }
}
