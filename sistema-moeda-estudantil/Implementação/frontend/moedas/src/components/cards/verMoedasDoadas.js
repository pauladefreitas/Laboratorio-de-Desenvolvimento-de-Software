import React from 'react';
import Card from '@mui/material/Card';
import './verMoedasDoadas.css';
import Moeda from '../../images/Logo.png'

const VerMoedasDoadas = ({ doacao }) => {
    return (
        <Card sx={{ maxWidth: 400, marginBottom: 2 }}>
            <span className='dateCoin'>{doacao.dataTransacao}</span>
            <div className='msg'>
                <img src={Moeda} style={{ width: '30px' }}></img>
                <h5>
                    Voce recebeu <span className='qtdMoeda'>{doacao.moedas} moeda(s)</span> do professor {doacao.professor.nome}
                </h5>
                <p>{doacao.descricao}</p>
            </div>
        </Card>
    );
}

export default VerMoedasDoadas;
