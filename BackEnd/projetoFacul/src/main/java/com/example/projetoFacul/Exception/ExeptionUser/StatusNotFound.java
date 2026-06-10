package com.example.projetoFacul.Exception.ExeptionUser;

public class StatusNotFound extends RuntimeException{

    public StatusNotFound(String mensagem){
        super(mensagem);
    }
}
