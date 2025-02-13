import React from 'react';
import { useNavigate } from 'react-router-dom';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import Moedas from '../../images/iconMoeda.png';

const CardVantagem = ({ id, nome, descricao, valor, fotoUrl }) => {
  const navigate = useNavigate();

  const handleCardClick = () => {
    navigate(`/detalhesVantagem/${id}`);
  };

  console.log("🚀 ~ CardVantagem ~ fotoUrl:", fotoUrl);

  return (
    <Card
      sx={{ minWidth: 275, margin: '10px 30%' }}
      onClick={handleCardClick}
      style={{ cursor: 'pointer' }}
    >
      <CardContent>
        <img
          src={fotoUrl}
          alt={nome}
          style={{
            width: '100%', 
            height: '200px',
            objectFit: 'cover', 
            borderRadius: '8px',
          }}
        />
        <Typography variant="h5" component="div" sx={{ marginBottom: 2 }}>
          {nome}
        </Typography>
        <Typography variant="body2" color="text.secondary" sx={{ marginBottom: 2 }}>
          {descricao}
        </Typography>
        <Box sx={{ display: 'flex', alignItems: 'center' }}>
          <img
            src={Moedas}
            alt="Ícone de Moeda"
            style={{
              width: 40,
              height: 40,
              marginRight: 1,
            }}
          />
          <Typography variant="h6">
            {valor} Moedas
          </Typography>
        </Box>
      </CardContent>
    </Card>
  );
};

export default CardVantagem;
