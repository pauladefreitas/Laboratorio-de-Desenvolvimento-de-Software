import * as React from 'react';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';
import perfil from '../../images/perfil.png';

const DoacoesRealizadas = ({ transaction }) => (
    <Card sx={{ maxWidth: 345, marginBottom: 2 }}>
        <CardHeader
            avatar={
                <Avatar
                    src={perfil}
                    alt="Quantidade de Moedas"
                    sx={{ width: 50, height: 50 }}
                    aria-label="Quantidade de Moedas"
                />
            }
            title={
                <Typography variant="body1">
                    {transaction.descricao}
                </Typography>
            }
            subheader={transaction.aluno.nome}
        />
        <Typography variant="body2" color="text.secondary" sx={{ paddingLeft: 2, paddingBottom: 1 }}>
            Data do envio: {transaction.dataTransacao} - {transaction.moedas} moedas
        </Typography>
    </Card>
);

export default DoacoesRealizadas;
