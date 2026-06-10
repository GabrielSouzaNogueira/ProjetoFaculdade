import React, { useState, useEffect } from "react";
import "./AdicionarProduto.css";

export default function AdicionarProduto() {
    // Estados para os campos do produto
    const [codBarra, setCodBarra] = useState("");
    const [descProduto, setDescProduto] = useState("");
    const [precoCusto, setPrecoCusto] = useState("");
    const [precoVenda, setPrecoVenda] = useState("");
    const [quantidade, setQuantidade] = useState("");
    
    // Estados para os Status (vindo do banco)
    const [listaStatus, setListaStatus] = useState([]);
    const [statusSelecionado, setStatusSelecionado] = useState("");
    
    const [mensagem, setMensagem] = useState("");

    // 1. Busca os status disponíveis no banco de dados ao carregar a tela
    useEffect(() => {
        const carregarStatus = async () => {
            const token = localStorage.getItem("meuToken");
            try {
                // Ajuste a URL conforme sua API (ex: /api/status ou /status-produto)
                const resposta = await fetch("http://localhost:8080/statusProd/listarStatus", {
                    headers: { "Authorization": `Bearer ${token}` }
                });
                if (resposta.ok) {
                    const dados = await resposta.json();
                    setListaStatus(dados);
                }
            } catch (error) {
                console.error("Erro ao carregar status:", error);
            }
        };
        carregarStatus();
    }, []);

    const handleSalvar = async (e) => {
        e.preventDefault();
        const token = localStorage.getItem("meuToken");

        // Monta o objeto exatamente como o seu banco espera (visto na foto)
        const novoProduto = {
            codBarra: codBarra,
            descProduto: descProduto,
            precoCusto: parseFloat(precoCusto),
            precoVenda: parseFloat(precoVenda),
            quantidade: parseInt(quantidade),
            prod_status_id: parseInt(statusSelecionado) // Envia o ID do status escolhido
        };

        try {
            const resposta = await fetch("http://localhost:8080/produto/cadastro", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                },
                body: JSON.stringify(novoProduto)
            });

            if (resposta.ok) {
                alert("Produto cadastrado com sucesso!");
                
                // Limpa o formulário
                setCodBarra("");
                setDescProduto("");
                setPrecoCusto("");
                setPrecoVenda("");
                setQuantidade("");
                setStatusSelecionado("");
            } else {
                setMensagem("Erro ao salvar. Verifique os dados.");
            }
        } catch (error) {
            setMensagem("Erro de conexão com o servidor.");
        }
    };

    return (
        <div className="adicionarProduto-container">
            <h1>Novo Produto</h1>
            {mensagem && <p className="mensagem-erro">{mensagem}</p>}
            
            <form onSubmit={handleSalvar} className="form-adicionar">
                <input type="text" placeholder="Código de Barras" value={codBarra} onChange={(e) => setCodBarra(e.target.value)} required />
                <input type="text" placeholder="Descrição do Produto" value={descProduto} onChange={(e) => setDescProduto(e.target.value)} required />
                
                <div className="input-group">
                    <input type="number" step="0.01" placeholder="Preço Custo" value={precoCusto} onChange={(e) => setPrecoCusto(e.target.value)} required />
                    <input type="number" step="0.01" placeholder="Preço Venda" value={precoVenda} onChange={(e) => setPrecoVenda(e.target.value)} required />
                </div>

                <input type="number" placeholder="Quantidade em Estoque" value={quantidade} onChange={(e) => setQuantidade(e.target.value)} required />

                {/* CAMPO SELECT PARA O STATUS */}


                <button type="submit" className="btn-enviar">Cadastrar Produto</button>
            </form>
        </div>
    );
}