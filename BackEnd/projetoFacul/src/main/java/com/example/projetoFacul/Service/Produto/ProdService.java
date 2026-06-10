package com.example.projetoFacul.Service.Produto;

import java.math.BigDecimal;
import java.util.List;

import com.example.projetoFacul.Exception.ExceptionProd.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.projetoFacul.DTOS.Produto.ProdDTO;
import com.example.projetoFacul.Exception.ExeptionUser.StatusNotFound;
import com.example.projetoFacul.Model.Produto.Produto;
import com.example.projetoFacul.Model.Produto.StatusProduto;
import com.example.projetoFacul.Repository.Produto.ProdStatusRepository;
import com.example.projetoFacul.Repository.Produto.ProdutoRepository;


@Service
public class ProdService {

    private final ProdStatusRepository prodStatusRepository;
    private final ProdutoRepository prodRepository;

    public ProdService(ProdutoRepository prodRepository, ProdStatusRepository prodStatusRepository){
        this.prodRepository = prodRepository;
        this.prodStatusRepository = prodStatusRepository;
    }

    public List<Produto> listarProdutoAtivo() {

        List<Produto> listados = prodRepository.findByStatusProdutoId(1L);

        if (listados.isEmpty()) {
            throw new SemRegistroProd("Sem produtos ativos cadastrados no sistema");
        }

        return listados;
    }

    @Transactional
    public Produto cadastroProd(ProdDTO dto) {

        if (dto.descProduto() == null || dto.descProduto().isBlank()) {
            throw new DescProdNotNull("A descrição do produto não pode estar vazia");
        }

        if(prodRepository.findByDescProdutoIgnoreCase(dto.descProduto()).isPresent()) {

            throw new DescProdutoExistente("Descrição do produto já cadastrado no sistema");
        }

        if("0".equals(dto.codBarra())){
            throw  new CodBarraNotNull("O código de barras não pode estar vazio ou estar null");
        }

        if (dto.codBarra().length() < 13) {

            throw new CodBarraBequeno("Código de barras menor que 13 digitos");
        }

        BigDecimal precoCusto = dto.precoCusto();
        if (precoCusto == null || precoCusto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new PrecoCustoNotBellowZero("O preço de custo não pode estar vazio ou ser menor ou igual a zero");
        }


        BigDecimal precoVenda = dto.precoVenda();
        if (precoVenda == null || precoVenda.compareTo(BigDecimal.ZERO) <= 0) {
            throw new PrecoVendaNotBellowZero("O preço de venda não pode estar vazio e ser menor ou igual a zero");
        }

        if (precoVenda.compareTo(precoCusto) < 0) {
            throw new PrecoVendaMenorQueCusto("O preço de venda não pode ser menor que o preço de custo");
        }

        StatusProduto statusProd = prodStatusRepository
                .findByStatusIgnoreCase("ATIVO")
                .orElseThrow(() -> new StatusNotFound("Status 'ATIVO' não encontrado"));

        Produto produto = new Produto(
                dto.descProduto(),
                dto.codBarra(),
                dto.precoCusto(),
                dto.precoVenda(),
                dto.quantidade(),
                statusProd,
                statusProd.getStatus()
        );

        prodRepository.save(produto);

        return produto;
    }

    @Transactional
    public Boolean softDeleteProd(Long id) {

        Produto produto = prodRepository.findById(id).orElseThrow(() -> new ProdNaoEncontrado("Produto com ID: " + id + " Não foi encontrado"));

        StatusProduto status = prodStatusRepository.findById(2L).orElseThrow(() -> new StatusNotFound("Status com ID 2 não encontrado"));


        if (produto.getStatusProduto() != null && produto.getStatusProduto().getProdStatus_id().equals(status.getProdStatus_id())) {

            return false;
        }

        produto.setStatusProduto(status);
        produto.setStatus(status.getStatus());

        prodRepository.save(produto);

        return true;

    }

}