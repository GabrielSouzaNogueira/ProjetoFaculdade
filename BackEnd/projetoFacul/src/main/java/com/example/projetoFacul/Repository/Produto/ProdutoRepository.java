package com.example.projetoFacul.Repository.Produto;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetoFacul.Model.Produto.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {


    List<Produto> findByStatusProdutoId(Long id);

    Optional<Produto> findByDescProdutoIgnoreCase(String descProduto);

}