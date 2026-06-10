package com.example.projetoFacul.Exception.ExceptionProd;

public class PrecoCustoNotBellowZero extends RuntimeException{

    public PrecoCustoNotBellowZero(String mensagem){
        super(mensagem);
    }
}
