package com.example.projetoFacul.Exception.ExceptionProd;

public class PrecoVendaNotBellowZero extends RuntimeException{

    public PrecoVendaNotBellowZero(String mensagem){
        super(mensagem);
    }
}
