package com.example.projetoFacul.Model.Usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    private String nome;
    private String senha;

    private String descCargo;
    private String statusUser;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private UserCargo cargo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "status_id")
    private  UserStatus status;


    public Usuario(){

    }

    public Usuario(String nome, String senha,String descCargo, UserCargo cargo, UserStatus status, String statusUser) {
        this.nome = nome == null || nome.isBlank() ? null : nome;
        this.senha = senha == null || senha.isBlank() ? null : senha;
        this.cargo = cargo;
        this.descCargo = descCargo;
        this.status = status;
        this.statusUser = statusUser;
    }

    public Long getUserId() {
        return userId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UserCargo getCargo() {
        return cargo;
    }

    public void setCargo(UserCargo cargo) {
        this.cargo = cargo;
    }

    public String getDescCargo() {
        return descCargo;
    }

    public void setDescCargo(String descCargo) {
        this.descCargo = descCargo;
    }

    public String getStatusUser() {
        return statusUser;
    }

    public void setStatusUser(String statusUser) {
        this.statusUser = statusUser;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
