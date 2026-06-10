import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Produtos from "./Produtos"; 
import AdicionarProduto from "./AdicionarProduto";
import Usuarios from "./Usuarios"; // <-- Importando o novo componente
import "./Dashboard.css"; 

export default function Dashboard() {
    const navigate = useNavigate();
    const [telaAtiva, setTelaAtiva] = useState("home");

    useEffect(() => {
        const tokenGuardado = localStorage.getItem("meuToken");
        if (!tokenGuardado) {
            navigate("/");
        }
    }, [navigate]);

    const handleLogout = () => {
        localStorage.removeItem("meuToken");
        localStorage.removeItem("nomeUsuario");
        navigate("/");
    };

    // Função que decide o que aparece no meio da tela
    const conteudo = () => {
        switch (telaAtiva) {
            case "home":
                return (
                    <div className="home-container">
                        <h2>Bem-vindo ao Sistema!</h2>
                        <p>Olá, <strong>{localStorage.getItem("nomeUsuario")}</strong>. Use o menu ao lado para navegar.</p>
                    </div>
                );
            case "produtos":
                return <Produtos />;
            case "adicionarProduto":
                return <AdicionarProduto />;
            case "usuarios":
                return <Usuarios />; 
            default:
                return null;
        }
    };

    return (
        <div className="dashboard-layout">
            <aside className="sidebar">
                <div className="sidebar-header">
                    <h3>PJ Facul - Gestão</h3>
                </div>

                <nav className="menu-lateral">
                    <button
                        className={`menu-btn ${telaAtiva === "home" ? "ativo" : ""}`}
                        onClick={() => setTelaAtiva("home")}
                    >
                         Início
                    </button>

                    <button
                        className={`menu-btn ${telaAtiva === "produtos" ? "ativo" : ""}`}
                        onClick={() => setTelaAtiva("produtos")}
                    >
                         Lista de Produtos
                    </button>

                    <button
                        className={`menu-btn ${telaAtiva === "adicionarProduto" ? "ativo" : ""}`}
                        onClick={() => setTelaAtiva("adicionarProduto")}
                    >
                        Novo Produto
                    </button>

                    {/* --- NOVO BOTÃO DE USUÁRIOS --- */}
                    <button
                        className={`menu-btn ${telaAtiva === "usuarios" ? "ativo" : ""}`}
                        onClick={() => setTelaAtiva("usuarios")}
                    >
                         Gerenciar Usuários
                    </button>

                    <button className="menu-btn btn-sair" onClick={handleLogout}>
                         Sair
                    </button>
                </nav>
            </aside>
            
            <main className="conteudo-principal">
                {conteudo()}
            </main>
        </div>
    );
}