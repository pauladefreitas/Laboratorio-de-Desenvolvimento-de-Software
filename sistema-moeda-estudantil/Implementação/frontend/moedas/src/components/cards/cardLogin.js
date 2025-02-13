import * as React from 'react';
import { useNavigate } from 'react-router-dom';
import Card from '@mui/material/Card';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import CardContent from '@mui/material/CardContent';
import Logo from '../../images/porco.png';
import Button from '@mui/material/Button';
import axios from 'axios';

const CardLogin = () => {
    const [email, setEmail] = React.useState('');
    const [senha, setSenha] = React.useState('');
    const navigate = useNavigate();

    const handleLogin = async () => {
        try {
            const response = await axios.post('http://localhost:8080/auth/login', {
                email,
                senha
            });

            localStorage.setItem('userId', response.data.id);
            localStorage.setItem('role', response.data.role);

            if (response.data.role === 'ALUNO') {
                navigate('/vizualizarVantagem');
            } else {
                alert('Login realizado com sucesso!');
                navigate('/vizualizarVantagem');
            }
        } catch (error) {
            console.error('Erro no login:', error.response?.data || error.message);
            alert('Erro ao fazer login. Verifique suas credenciais.');
        }
    };


    return (
        <Card sx={{ minWidth: 275, margin: '35px 30%' }}>
            <CardContent sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                <Box
                    component="img"
                    src={Logo}
                    alt="Logo"
                    sx={{ display: { xs: 'none', md: 'flex' }, width: 100, height: 100 }}
                />
                <h1>Login</h1>
                <p>Bem-vindo, faça login para continuar</p>
                <TextField
                    style={{ width: '300px' }}
                    id="outlined-email"
                    label="Email"
                    variant="outlined"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />
                <TextField
                    style={{ width: '300px' }}
                    sx={{ marginTop: '25px' }}
                    id="outlined-password"
                    label="Senha"
                    variant="outlined"
                    type="password"
                    value={senha}
                    onChange={(e) => setSenha(e.target.value)}
                />
                <Button
                    sx={{ background: '#191970', marginTop: '25px' }}
                    style={{ width: '300px' }}
                    variant="contained"
                    onClick={handleLogin}
                >
                    Login
                </Button>
            </CardContent>
        </Card>
    );
};

export default CardLogin;
