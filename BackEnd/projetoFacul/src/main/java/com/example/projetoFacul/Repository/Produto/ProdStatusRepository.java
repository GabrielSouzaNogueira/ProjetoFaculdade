package com.example.projetoFacul.Repository.Produto;

import com.example.projetoFacul.Model.Produto.Produto;
import com.example.projetoFacul.Model.Produto.StatusProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdStatusRepository extends JpaRepository<StatusProduto, Long> {

    Optional<StatusProduto> findByStatusIgnoreCase(String status);

    List<StatusProduto> findByStatus(String status);



}
