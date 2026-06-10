package com.example.projetoFacul.Exception.ExeptionUser;

public class UserNotFound extends RuntimeException{

    public UserNotFound(String mensagem){
        super(mensagem);
    }
}
