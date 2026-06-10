const API_URL = "http://localhost:3000";

export const api = {
    // Busca o token atualizado do storage
    getToken: () => localStorage.getItem("meuToken"),

    // GET - Listar produtos
    getProdutos: async () => {
        const response = await fetch(`${API_URL}/produtos`, {
            headers: { 'Authorization': `Bearer ${localStorage.getItem("meuToken")}` }
        });
        return response.json();
    },

    // POST - Criar produto
    createProduto: async (dados) => {
        return fetch(`${API_URL}/produtos`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem("meuToken")}`
            },
            body: JSON.stringify(dados)
        });
    },

    // DELETE - Excluir produto
    deleteProduto: async (id) => {
        return fetch(`${API_URL}/produtos/${id}`, {
            method: 'DELETE',
            headers: { 'Authorization': `Bearer ${localStorage.getItem("meuToken")}` }
        });
    }
};