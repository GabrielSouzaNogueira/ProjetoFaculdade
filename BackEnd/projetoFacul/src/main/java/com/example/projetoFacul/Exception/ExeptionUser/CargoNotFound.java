package com.example.projetoFacul.Exception.ExeptionUser;

public class CargoNotFound extends RuntimeException{

    public CargoNotFound(String mensagem) {
        super(mensagem);
    }
}
