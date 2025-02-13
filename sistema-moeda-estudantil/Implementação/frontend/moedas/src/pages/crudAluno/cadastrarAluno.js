import React, { useState } from 'react';
import Header from '../../components/Header';
import { Button, TextField } from '@mui/material';
import axios from 'axios';
import './cadastrarAluno.css';

const CadastrarAluno = () => {
    const [formData, setFormData] = useState({
        nome: '',
        email: '',
        endereco: '',
        // saldoMoedas: 0,
        curso: '',
        rg: '',
        cpf: '',
        instituicaoEnsino: 1,
        senha: ''
    });
    const [cpf, setCpf] = useState('');
    const [senha, setSenha] = useState('')

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value
        }));
    };

    const handleCpfChange = (event) => {
        let value = event.target.value.replace(/\D/g, '');

        if (value.length > 3 && value.length <= 6) {
            value = value.replace(/(\d{3})(\d+)/, '$1.$2');
        } else if (value.length > 6 && value.length <= 9) {
            value = value.replace(/(\d{3})(\d{3})(\d+)/, '$1.$2.$3');
        } else if (value.length > 9) {
            value = value.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4');
        }

        setCpf(value);
        setFormData((prevData) => ({
            ...prevData,
            cpf: value
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post(`http://localhost:8080/aluno?senha=${senha}`, {
                ...formData,
                instituicaoEnsino: {
                    id: parseInt(formData.instituicaoEnsino)
                }
            });
            alert('Aluno cadastrado com sucesso!');
            setFormData({
                nome: '',
                email: '',
                endereco: '',
                // saldoMoedas: 0,
                curso: '',
                rg: '',
                cpf: '',
                instituicaoEnsino: 1,
            });
        } catch (error) {
            alert('Erro ao cadastrar aluno: ' + error.response?.data || error.message);
        }
    };

    return (
        <div>
            <Header />
            <h1 className='title'>Cadastrar aluno</h1>
            <hr className='divider' />

            <div className='cadAluno'>
                <form onSubmit={handleSubmit} className='dadosAluno'>
                    <TextField
                        label="Nome"
                        variant="standard"
                        name="nome"
                        value={formData.nome}
                        onChange={handleChange}
                        required
                    />
                    <TextField
                        label="Email"
                        variant="standard"
                        name="email"
                        type="email"
                        value={formData.email}
                        onChange={handleChange}
                        required
                    />
                    <TextField
                        label="Endereço"
                        variant="standard"
                        name="endereco"
                        value={formData.endereco}
                        onChange={handleChange}
                        required
                    />
                    {/* <TextField
                        label="Saldo em Moedas"
                        variant="standard"
                        name="saldoMoedas"
                        type="number"
                        value={formData.saldoMoedas}
                        onChange={handleChange}
                        required
                    /> */}
                    <TextField
                        label="Curso"
                        variant="standard"
                        name="curso"
                        value={formData.curso}
                        onChange={handleChange}
                        required
                    />
                    <TextField
                        label="RG"
                        variant="standard"
                        name="rg"
                        value={formData.rg}
                        onChange={handleChange}
                        required
                    />
                    <TextField
                        label="CPF"
                        variant="standard"
                        name="cpf"
                        value={cpf}
                        onChange={handleCpfChange}
                        required
                    />
                    <TextField
                        label="Instituição de Ensino ID"
                        variant="standard"
                        name="instituicaoEnsino"
                        type="number"
                        value={formData.instituicaoEnsino}
                        onChange={handleChange}
                        required
                    />
                    <TextField
                        label="Senha"
                        variant="standard"
                        name="senha"
                        type="password"
                        value={senha}
                        onChange={(e) => setSenha(e.target.value)} 
                        required
                    />
                    <Button
                        type="submit"
                        className='btn'
                        variant="contained"
                        sx={{ background: '#191970' }}
                        style={{ width: '300px' }}
                    >
                        Cadastrar aluno
                    </Button>
                </form>
            </div>
        </div>
    );
};

export default CadastrarAluno;
