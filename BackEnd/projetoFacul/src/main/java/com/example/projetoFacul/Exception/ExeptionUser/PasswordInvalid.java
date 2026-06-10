package com.example.projetoFacul.Exception.ExeptionUser;

public class PasswordInvalid extends RuntimeException{
    public PasswordInvalid(String mensagem){
        super(mensagem);
    }
}
