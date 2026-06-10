package com.example.projetoFacul.Model.Produto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class StatusProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String status;

    @JsonIgnore
    @OneToMany(mappedBy = "statusProduto", fetch = FetchType.LAZY)
    private List<Produto> produtos;

    public StatusProduto() { }

    public Long getProdStatus_id() {
        return id;
    }

    public void setProdStatus_id(Long prodStatus_id) {
        this.id = prodStatus_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}