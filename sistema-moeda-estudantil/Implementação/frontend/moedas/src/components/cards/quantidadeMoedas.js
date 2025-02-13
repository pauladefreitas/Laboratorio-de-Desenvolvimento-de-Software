import * as React from 'react';
import { useEffect, useState } from 'react';
import axios from 'axios';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';
import qtdMoeda from '../../images/qtdMoedas.png';

const RecipeReviewCard = () => {
  const [saldoMoedas, setSaldoMoedas] = useState(0);

  useEffect(() => {
    axios.get('http://localhost:8080/gerenciador_moedas/professor/87')
      .then((response) => {
        const saldo = response.data[0]?.professor?.saldoMoedas;
        setSaldoMoedas(saldo);
      })
      .catch((error) => {
        console.error('Erro ao buscar a quantidade de moedas:', error);
      });
  }, []);

  return (
    <Card sx={{ maxWidth: 345 }}>
      <CardHeader
        avatar={
          <Avatar
            src={qtdMoeda}
            alt="Quantidade de Moedas"
            sx={{ width: 50, height: 50 }}
            aria-label="Quantidade de Moedas"
          />
        }
        title={
          <Typography variant="body1">
            Você tem <span style={{ fontWeight: 'bold' }}>{saldoMoedas}</span> moedas para doar
          </Typography>
        }
      />
    </Card>
  );
}

export default RecipeReviewCard;
