package com.example.projetoFacul.Exception.ExeptionUser;

public class PasswordNotNull extends  RuntimeException{

    public PasswordNotNull(String mensagem) {
        super(mensagem);
    }
}
