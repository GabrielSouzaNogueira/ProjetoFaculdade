import React from 'react';
import './App.css'

// Importações essenciais do react-router-dom para fazer a navegação funcionar
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Login from './Login';
import Dashboard from './Dashboard';

function App() {
  return (
    // BrowserRouter: É o "envelopador" principal. Ele diz ao React que este app usará navegação web.
    <BrowserRouter>
      
   
      <Routes>
        
        <Route path="/" element={<Login />} />

        <Route path="/dashboard" element={<Dashboard/>}/>
        
      </Routes>
    </BrowserRouter>
  )
}

export default App