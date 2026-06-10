package com.example.projetoFacul.Service.Produto;

import com.example.projetoFacul.Exception.ExceptionProd.SemRegistroProd;
import com.example.projetoFacul.Model.Produto.StatusProduto;
import com.example.projetoFacul.Repository.Produto.ProdStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdStatusService {

    ProdStatusRepository prodStatusRepository;

    public ProdStatusService(ProdStatusRepository prodStatusRepository) {
        this.prodStatusRepository = prodStatusRepository;
    }

    public List<StatusProduto> listarStatus() {

        List<StatusProduto> listados = prodStatusRepository.findByStatus("ATIVO");

        if(listados.isEmpty()) {

            throw new SemRegistroProd("Sem status registrados no sistema");
        }

        return listados;

    }
}
