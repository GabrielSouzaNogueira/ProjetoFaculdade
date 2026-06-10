import React, { useState, useEffect } from "react";
import "./Usuarios.css";

export default function Usuarios() {
    const [usuarios, setUsuarios] = useState([]);
    
    // Campos do formulário (Status removido)
    const [nome, setNome] = useState("");
    const [senha, setSenha] = useState("");
    const [descCargo, setDescCargo] = useState("");
    const [cargoId, setCargoId] = useState("");

    // ESTADO PARA A LISTA DO BANCO (Status removido)
    const [listaCargos, setListaCargos] = useState([]);

    // BUSCAR OS DADOS AUXILIARES (CARGOS) AO ABRIR A TELA
    useEffect(() => {
        const carregarDadosAuxiliares = async () => {
            try {
                const resCargos = await fetch("http://localhost:8080/userCargo/listarCargosUser");
                if (resCargos.ok) {
                    const dadosCargos = await resCargos.json();
                    setListaCargos(dadosCargos);
                }
            } catch (erro) {
                console.error("Erro de conexão ao buscar dados auxiliares:", erro);
            }
        };

        carregarDadosAuxiliares();
    }, []); 

    // CARREGAR LISTA DE USUÁRIOS
    const buscarUsuarios = async () => {
        try {
            const res = await fetch("http://localhost:8080/usuario/listarUser");
            if (res.ok) {
                const dados = await res.json();
                setUsuarios(dados);
            }
        } catch (e) { 
            console.error("Erro ao buscar usuários", e); 
        }
    };

    useEffect(() => { 
        buscarUsuarios(); 
    }, []); 

    // Função que roda quando escolhemos um cargo no Select
    const handleSelecionarCargo = (e) => {
        const idEscolhido = e.target.value;
        setCargoId(idEscolhido); 

        const cargoEncontrado = listaCargos.find(item => item.cargoId.toString() === idEscolhido);
        
        if (cargoEncontrado) {
            setDescCargo(cargoEncontrado.cargo); 
        } else {
            setDescCargo("");
        }
    };

    // FUNÇÃO PARA SALVAR NOVO USUÁRIO
    const handleSalvar = async (e) => {
        e.preventDefault();
        
        const novoUsuario = {
            nome: nome,
            senha: senha,
            descCargo: descCargo,
            cargoId: parseInt(cargoId)
        };

        try {
            const res = await fetch("http://localhost:8080/usuario/cadastro", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(novoUsuario)
            });

            if (res.ok) {
                alert("Usuário cadastrado com sucesso!");
                setNome(""); 
                setSenha(""); 
                setDescCargo("");
                setCargoId("");
                buscarUsuarios();
            } else {
                alert("Erro ao cadastrar usuário. Verifique os dados.");
            }
        } catch (e) { 
            alert("Erro de conexão ao cadastrar usuário"); 
        }
    };

    // FUNÇÃO PARA DELETAR USUÁRIO
    const handleExcluir = async (id) => {
        const confirmar = window.confirm("Tem certeza que deseja excluir este usuário?");
        if (!confirmar) return;

        try {

            // Verifique se essa é exatamente a rota configurada no seu back-end Java
            const res = await fetch(`http://localhost:8080/usuario/delete/${id}`, {
                method: "PATCH",
            });

            if (res.ok) {
                alert("Usuário excluído com sucesso!");
                buscarUsuarios(); // Atualiza a lista na tela automaticamente
            } else {
                alert("Erro ao excluir usuário.");
            }
        } catch (e) {
            console.error("Erro de conexão ao excluir usuário", e);
            alert("Erro de conexão ao excluir usuário.");
        }
    };

    return (
        <div className="usuarios-container">
            <div className="cadastro-secao">
                <h2>Cadastrar Novo Usuário</h2>
                <form onSubmit={handleSalvar} className="form-usuarios">
                    <input type="text" placeholder="Nome do Usuário" value={nome} onChange={e => setNome(e.target.value)} required />
                    <input type="password" placeholder="Senha" value={senha} onChange={e => setSenha(e.target.value)} required />
                    
                    {/* INPUT DE DESCRIÇÃO BLOQUEADO */}
                    <input 
                        type="text" 
                        placeholder="Descrição do Cargo (Automático)" 
                        value={descCargo} 
                        readOnly 
                        style={{ backgroundColor: "#e9ecef", cursor: "not-allowed" }} 
                    />
                    
                    <div className="input-group">
                        {/* SELECT DE CARGO */}
                        <select value={cargoId} onChange={handleSelecionarCargo} required>
                            <option value="" disabled>Selecione o Cargo</option>
                            {listaCargos.map((item) => (
                                <option key={item.cargoId} value={item.cargoId}>
                                    {item.cargo}
                                </option>
                            ))}
                        </select>
                    </div>

                    <button type="submit" className="btn-salvar">Criar Usuário</button>
                </form>
            </div>

            <hr className="divisor" />

            <div className="lista-secao">
                <h2>Usuários Cadastrados</h2>
                <table className="tabela-padrao">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>Cargo</th>
                            <th>Status</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        {usuarios.map(u => (
                            <tr key={u.id || u.userId || u.user_id}>
                                <td>{u.id || u.userId || u.user_id}</td>
                                <td>{u.nome}</td>
                                <td>{u.descCargo || (u.cargo ? u.cargo.cargo : "Sem cargo")}</td>
                                <td>{u.statusUser || (u.status ? u.status.status : "Sem status")}</td>
                                <td>
                                    {/* BOTÃO DE EXCLUIR CONECTADO À FUNÇÃO */}
                                    <button 
                                        className="btn-excluir-mini" 
                                        onClick={() => handleExcluir(u.id || u.userId || u.user_id)}
                                        title="Excluir usuário"
                                    >
                                        🗑️
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}