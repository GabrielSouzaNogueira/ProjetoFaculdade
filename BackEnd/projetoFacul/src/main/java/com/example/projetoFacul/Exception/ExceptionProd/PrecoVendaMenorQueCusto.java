package com.example.projetoFacul.Exception.ExceptionProd;

public class PrecoVendaMenorQueCusto extends RuntimeException{

    public PrecoVendaMenorQueCusto(String mensagem){
        super(mensagem);
    }
}
