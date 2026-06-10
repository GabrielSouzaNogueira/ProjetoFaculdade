package com.example.projetoFacul.Api.Produto;

import java.time.Instant;
import java.util.List;

import com.example.projetoFacul.Model.Produto.Produto;
import org.apache.juli.logging.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.projetoFacul.DTOS.Produto.ProdDTO;
import com.example.projetoFacul.DTOS.ResponseDTO;
import com.example.projetoFacul.Service.Produto.ProdService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdService prodService;

    public ProdutoController(ProdService prodService) {
        this.prodService = prodService;
    }


    @GetMapping("/listarProd")
    public ResponseEntity<List<Produto>> listProdAtivo() {

        List<Produto> listados = prodService.listarProdutoAtivo();

        return ResponseEntity.ok().body(listados);

    }


    @PostMapping("/cadastro")
    public ResponseEntity<ResponseDTO> cadastroProduto(@RequestBody ProdDTO dto) {

        prodService.cadastroProd(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(
                true,
                "Produto Cadastrado com Sucesso",
                Instant.now().toString()
        ));

    }

    @PatchMapping("/soft-Delete/{id}")
    public ResponseEntity<ResponseDTO> softDelete(@PathVariable Long id) {

        Boolean excluido = prodService.softDeleteProd(id);


        return ResponseEntity.ok().body(new ResponseDTO(
                true,
                "Produto cadastrado com sucesso",
                Instant.now().toString()
        ));

    }

}
