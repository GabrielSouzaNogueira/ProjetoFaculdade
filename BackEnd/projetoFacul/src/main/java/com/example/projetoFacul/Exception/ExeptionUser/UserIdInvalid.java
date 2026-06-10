package com.example.projetoFacul.Exception.ExeptionUser;

public class UserIdInvalid extends RuntimeException{

    public UserIdInvalid(String mensagem) {
        super(mensagem);
    }
}
