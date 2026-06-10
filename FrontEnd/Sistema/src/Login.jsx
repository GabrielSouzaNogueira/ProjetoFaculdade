import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Login.css";

export default function Login() {
  // useState: Cria "caixinhas" na memória para guardar o que o usuário digita em tempo real.
  const [nome, setNome] = useState("");
  const [senha, setSenha] = useState("");
  const [erro, setErro] = useState(""); // Guarda a mensagem de erro, se houver.

  // useNavigate: Cria uma função que permite "empurrar" o usuário para outra página via código.
  const Navigate = useNavigate();

  // Função disparada quando o usuário clica em "Entrar" (ou aperta Enter no formulário)
  const handleLogin = async (evento) => {
    evento.preventDefault();
    setErro("");

    // --- 🚨 MODO DE TESTE / ADMINISTRADOR 🚨 ---
    if (nome === "admin" && senha === "123") {
      console.log("Login de administrador ativado!");
      
      // Criamos chaves falsas para o sistema achar que estamos logados de verdade
      localStorage.setItem("meuToken", "token-falso-de-teste-123"); 
      localStorage.setItem("nomeUsuario", "Admin");
      
      Navigate("/dashboard");
      return; // O 'return' faz a função parar aqui. Ele NÃO vai tentar chamar a API abaixo.
    }
    // -------------------------------------------

    // A partir daqui, é o código normal que vai tentar falar com a API real
    try {
      const resposta = await fetch("http://localhost:8080/usuario/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ 
          nome: nome, 
          senha: senha 
        }),
      });

      if (resposta.ok) {
        const dadosBackend = await resposta.json(); 
        localStorage.setItem("meuToken", dadosBackend.token); 
        localStorage.setItem("nomeUsuario", dadosBackend.nome); 
        Navigate("/dashboard");
      } else {
        const erroBackend = await resposta.json();
        setErro(erroBackend.mensagem || "Usuário ou senha inválidos!");
      }

    } catch (error) {
      console.error("Erro de conexão:", error);
      setErro("Não foi possível conectar ao servidor. Tente novamente mais tarde.");
    }
  };

  return (
    <div className="login-container">
      <div className="card">
        <div>
          <h1>Login</h1>
          {/* O form agrupa os inputs. Quando enviado (onSubmit), roda a função handleLogin */}
          <form onSubmit={handleLogin}>
            
            <input
              type="text"
              placeholder="Nome"
              value={nome} // O valor atual do input é o estado 'nome'
              onChange={(evento) => setNome(evento.target.value)} // Atualiza o estado quando o usuário digita
              required // Obriga o preenchimento antes de enviar
            />
            
            <input
              type="password" // Esconde os caracteres (***)
              placeholder="Senha"
              value={senha}
              onChange={(evento) => setSenha(evento.target.value)}
              required
            />

            {/* Renderização condicional: Se existir algum texto na variável 'erro', mostra essa div */}
            {erro && <div className="mensagem-erro">{erro}</div>}

            <button type="submit">
              Entrar
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}