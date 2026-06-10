package com.example.projetoFacul.Exception.ExeptionUser;

public class NameNotFound extends RuntimeException{
    public NameNotFound(String mensagem){
        super(mensagem);
    }
}
