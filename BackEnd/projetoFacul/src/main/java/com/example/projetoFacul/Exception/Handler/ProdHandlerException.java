package com.example.projetoFacul.Exception.Handler;

import com.example.projetoFacul.DTOS.ResponseDTO;
import com.example.projetoFacul.Exception.ExceptionProd.*;
import com.example.projetoFacul.Exception.ExceptionProd.PrecoCustoNotBellowZero;
import com.example.projetoFacul.Exception.ExceptionProd.PrecoVendaNotBellowZero;
import com.example.projetoFacul.Exception.ExeptionUser.StatusNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ProdHandlerException {

    @ExceptionHandler(DescProdNotNull.class)
    public ResponseEntity<ResponseDTO> handlerNameNotNull(DescProdNotNull ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(false, "DESC_PROD_NOT_NULL", ex.getMessage(),Instant.now().toString()));
    }

    @ExceptionHandler(PrecoCustoNotBellowZero.class)
    public ResponseEntity<ResponseDTO> handlerNameNotNull(PrecoCustoNotBellowZero ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(false, "PRECO_CUSTO_NOT_NULL", ex.getMessage(),Instant.now().toString()));
    }

    @ExceptionHandler(PrecoVendaNotBellowZero.class)
    public ResponseEntity<ResponseDTO> handlerNameNotNull(PrecoVendaNotBellowZero ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(false, "PRECO_VENDA_NOT_NULL", ex.getMessage(), Instant.now().toString()));
    }

    @ExceptionHandler(StatusNotFound.class)
    public ResponseEntity<ResponseDTO> handlerNameNotNull(StatusNotFound ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(false, "STATUS_NOT_FOUND", ex.getMessage(),Instant.now().toString()));
    }

    @ExceptionHandler(PrecoVendaMenorQueCusto.class)
    public ResponseEntity<ResponseDTO> handlerNameNotNull(PrecoVendaMenorQueCusto ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(false, "PRECO_VENDA_MENOR_CUSTO", ex.getMessage(),Instant.now().toString()));
    }

    @ExceptionHandler(CodBarraNotNull.class)
    public ResponseEntity<ResponseDTO> handlerCodBarraNotNull(CodBarraNotNull ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(false, "CODBARRA_NOT_NULL", ex.getMessage(),Instant.now().toString()));
    }

    @ExceptionHandler(SemRegistroProd.class)
    public ResponseEntity<ResponseDTO> handlerCSemRegistroProd(SemRegistroProd ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(false, "SEM_REGISTRO_SISTEMA", ex.getMessage(),Instant.now().toString()));
    }

    @ExceptionHandler(DescProdutoExistente.class)
    public ResponseEntity<ResponseDTO> handlerDescProdutoExistente(DescProdutoExistente ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(false, "NOME_PROD_JA_EXISTENTE", ex.getMessage(),Instant.now().toString()));
    }

    @ExceptionHandler(CodBarraBequeno.class)
    public ResponseEntity<ResponseDTO> handlerCodBarraBequeno(CodBarraBequeno ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(false, "COD_BARRA_PEQUENO", ex.getMessage(),Instant.now().toString()));
    }

    @ExceptionHandler(ProdNaoEncontrado.class)
    public ResponseEntity<ResponseDTO> handlerProdNaoEncontrado(ProdNaoEncontrado ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(false, "PROD_NAO_ENCONTRADO", ex.getMessage(),Instant.now().toString()));
    }


    //ProdNaoEncontrado

}
