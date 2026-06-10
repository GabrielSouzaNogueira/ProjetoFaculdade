import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "./Produtos.css"; 

export default function Produtos() {
    const [produtos, setProdutos] = useState([]);
    const [carregando, setCarregando] = useState(true);
    const [erro, setErro] = useState("");
    
    // Estados para o Modal de Edição
    const [estaEditando, setEstaEditando] = useState(false);
    const [produtoParaEditar, setProdutoParaEditar] = useState(null);
    const [listaStatus, setListaStatus] = useState([]);

    const navigate = useNavigate();
    const token = localStorage.getItem("meuToken");
    const API_URL = "http://localhost:8080/produto/listarProd";

    useEffect(() => {
        buscarProdutos();
        buscarStatus(); 
    }, []);

    const buscarProdutos = async () => {
        try {
            const resposta = await fetch(API_URL, {
                headers: { "Authorization": `Bearer ${token}` }
            });
            if (resposta.ok) {
                const dados = await resposta.json();
                setProdutos(dados);
            }
        } catch (error) {
            setErro("Erro ao conectar com o servidor.");
        } finally {
            setCarregando(false);
        }
    };

    const buscarStatus = async () => {
        try {
            const resposta = await fetch("http://localhost:8080/statusProd/listarStatus", { // Mantive ajustado caso a porta seja 8080 também
                headers: { "Authorization": `Bearer ${token}` }
            });
            if (resposta.ok) {
                const dados = await resposta.json();
                setListaStatus(dados);
            }
        } catch (e) { console.error("Erro status", e); }
    };

    // MÉTODO DELETAR CORRIGIDO
    const handleDeletar = async (id) => {
        if (!window.confirm("Deseja realmente excluir (inativar) este produto?")) return;
        
        try {
            // O Java espera exatamente /soft-Delete/{id}
            const res = await fetch(`http://localhost:8080/produto/soft-Delete/${id}`, {
                method: "PATCH", 
                headers: { 
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}` 
                }
            });

            if (res.ok) {
                const dados = await res.json();
                alert(dados.message || "Produto inativado com sucesso!");
                buscarProdutos(); // Recarrega a lista para sumir o item
            } else {
                alert("Erro ao excluir o produto.");
            }
        } catch (e) { 
            alert("Erro de conexão ao tentar excluir."); 
        }
    };

    const abrirEdicao = (produto) => {
        setProdutoParaEditar({ ...produto });
        setEstaEditando(true);
    };

    const salvarEdicao = async (e) => {
        e.preventDefault();
        try {
            // Ajustado para produtoParaEditar.id conforme o Java
            const res = await fetch(`${API_URL}/${produtoParaEditar.id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                },
                body: JSON.stringify(produtoParaEditar)
            });

            if (res.ok) {
                alert("Atualizado com sucesso!");
                setEstaEditando(false);
                buscarProdutos(); 
            } else {
                alert("Erro ao atualizar o produto.");
            }
        } catch (e) { alert("Erro de conexão ao atualizar"); }
    };

    return (
        <div className="produtos-container">
            <h2>Lista de Estoque</h2>
            {carregando ? <p>Carregando...</p> : (
                <table className="tabela-produtos">
                    <thead>
                        <tr>
                            <th>Cód.</th>
                            <th>Descrição</th>
                            <th>Custo</th>
                            <th>Venda</th>
                            <th>Estoque</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        {produtos.map(p => (
                            <tr key={p.id}>
                                <td>{p.codBarra}</td>
                                <td>{p.descProduto}</td>
                                <td>R$ {p.precoCusto}</td>
                                <td>R$ {p.precoVenda}</td>
                                <td>{p.quantidade}</td>
                                <td>
                                    <button onClick={() => abrirEdicao(p)}>✏️</button>
                                    
                                    {/* AQUI ESTÁ A CORREÇÃO PRINCIPAL: Passando apenas p.id */}
                                    <button onClick={() => handleDeletar(p.id)}>🗑️</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}

            {/* MODAL DE EDIÇÃO */}
            {estaEditando && (
                <div className="modal-overlay">
                    <div className="modal-content">
                        <h3>Editar Produto</h3>
                        <form onSubmit={salvarEdicao}>
                            
                            {/* Ajustado para descProduto */}
                            <input 
                                type="text" 
                                value={produtoParaEditar.descProduto || ""} 
                                onChange={e => setProdutoParaEditar({...produtoParaEditar, descProduto: e.target.value})}
                                placeholder="Descrição"
                            />
                            
                            {/* Ajustado para precoVenda */}
                            <input 
                                type="number" 
                                step="0.01"
                                value={produtoParaEditar.precoVenda || ""} 
                                onChange={e => setProdutoParaEditar({...produtoParaEditar, precoVenda: e.target.value})}
                                placeholder="Preço Venda"
                            />
                            
                            {/* A quantidade já estava correta */}
                            <input 
                                type="number" 
                                value={produtoParaEditar.quantidade || ""} 
                                onChange={e => setProdutoParaEditar({...produtoParaEditar, quantidade: e.target.value})}
                                placeholder="Quantidade"
                            />
                            
                            <select 
                                value={produtoParaEditar.prod_status_id || ""}
                                onChange={e => setProdutoParaEditar({...produtoParaEditar, prod_status_id: e.target.value})}
                            >
                                <option value="">Selecione um Status</option>
                                {listaStatus.map(s => (
                                    <option key={s.status_id} value={s.status_id}>{s.desc_status}</option>
                                ))}
                            </select>

                            <div className="modal-actions">
                                <button type="submit">Salvar</button>
                                <button type="button" onClick={() => setEstaEditando(false)}>Cancelar</button>
                            </div>
                        </form>
                    </div>
                </div>
            )}
        </div>
    );
}