package com.example.projetoFacul.Exception.ExeptionUser;

public class NomeAlreadyExist extends RuntimeException{

    public NomeAlreadyExist(String mensagem){
        super(mensagem);
    }
}
