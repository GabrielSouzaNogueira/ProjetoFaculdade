package com.example.projetoFacul.Api.Produto;

import com.example.projetoFacul.Model.Produto.StatusProduto;
import com.example.projetoFacul.Service.Produto.ProdStatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statusProd")
public class ProdStatusController {

    ProdStatusService prodStatusService;

    public ProdStatusController(ProdStatusService prodStatusService) {
        this.prodStatusService = prodStatusService;
    }

    @GetMapping("/listarStatus")
    public ResponseEntity<List<StatusProduto>> list() {

        List<StatusProduto> listados = prodStatusService.listarStatus();

        return ResponseEntity.ok().body(listados);

    }


}
