import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import Header from '../../components/Header';
import axios from 'axios';
import {
  Card,
  CardContent,
  Typography,
  Button,
  Box,
  Snackbar,
  Alert,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogContentText,
  DialogActions,
} from '@mui/material';

function DetailsVantagem() {
  const { id } = useParams();
  const [vantagem, setVantagem] = useState(null);
  const [saldoMoedas, setSaldoMoedas] = useState(0);
  const [openSnackbar, setOpenSnackbar] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState('');
  const [snackbarSeverity, setSnackbarSeverity] = useState('success');
  const [openDialog, setOpenDialog] = useState(false);

  const userId = localStorage.getItem('userId');

  useEffect(() => {
    axios
      .get(`http://localhost:8080/vantagens/vantagem/${id}`)
      .then((response) => {
        setVantagem(response.data);
      })
      .catch((error) => {
        console.error('Erro ao buscar os detalhes da vantagem:', error);
      });

    if (userId) {
      axios
        .get(`http://localhost:8080/aluno/auth/${userId}`)
        .then((response) => {
          setSaldoMoedas(response.data.saldoMoedas);
        })
        .catch((error) => {
          console.error('Erro ao buscar saldo do aluno:', error);
        });
    }
  }, [id, userId]);

  const handleOpenDialog = () => {
    setOpenDialog(true);
  };

  const handleCloseDialog = () => {
    setOpenDialog(false);
  };

  const handleResgatarItem = () => {
    if (!userId || !vantagem) return;

    const resgateData = {
      vantagemId: vantagem.id,
      descricao: vantagem.descricao,
    };

    axios
      .post(`http://localhost:8080/aluno/${userId}/resgateVantagens`, resgateData)
      .then((response) => {
        setSaldoMoedas((prevSaldo) => prevSaldo - vantagem.valor);
        setSnackbarMessage('Vantagem resgatada com sucesso!');
        setSnackbarSeverity('success');
        setOpenSnackbar(true);
        setOpenDialog(false); 
      })
      .catch((error) => {
        console.error('Erro ao resgatar a vantagem:', error);
        setSnackbarMessage('Erro ao resgatar a vantagem.');
        setSnackbarSeverity('error');
        setOpenSnackbar(true);
        setOpenDialog(false); 
      });
  };

  const handleCloseSnackbar = () => {
    setOpenSnackbar(false);
  };

  if (!vantagem) {
    return <p>Carregando detalhes...</p>;
  }

  const hasEnoughCoins = saldoMoedas >= vantagem.valor;

  return (
    <div>
      <Header />
      <div className="details-container">
        <Box
          sx={{
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'center',
            gap: 2,
            marginTop: 3,
            flexWrap: 'wrap',
          }}
        >
          <Box
            component="img"
            src={vantagem.fotoUrl}
            alt={vantagem.nome}
            sx={{
              width: 150,
              height: 150,
              objectFit: 'cover',
              borderRadius: 8,
              boxShadow: 2,
            }}
          />

          <Box
            sx={{
              flex: 1,
              maxWidth: 500,
            }}
          >
            <Typography variant="h4" sx={{ fontWeight: 'bold' }}>
              {vantagem.nome}
            </Typography>
            <Typography variant="body1" sx={{ marginTop: 1 }}>
              <strong>Descrição: </strong>
              {vantagem.descricao}
            </Typography>
          </Box>

          <Card
            sx={{
              minWidth: 200,
              boxShadow: 3,
            }}
          >
            <CardContent>
              <Typography
                variant="h5"
                component="div"
                sx={{ fontWeight: 'bold', marginBottom: 1 }}
              >
                {vantagem.valor} Moedas
              </Typography>
              <Typography
                variant="body2"
                sx={{ color: 'green', fontWeight: 'bold' }}
              >
                Seu saldo atual: {saldoMoedas} Moedas
              </Typography>
              {hasEnoughCoins ? (
                <Typography
                  variant="body2"
                  sx={{ color: 'blue', fontWeight: 'bold' }}
                >
                  Saldo após resgate: {saldoMoedas - vantagem.valor} Moedas
                </Typography>
              ) : (
                <Typography
                  variant="body2"
                  sx={{ color: 'red', fontWeight: 'bold' }}
                >
                  Você não possui moedas suficientes para resgatar o item
                </Typography>
              )}
            </CardContent>
            <Box
              sx={{
                display: 'flex',
                justifyContent: 'center',
                padding: 2,
              }}
            >
              <Button
                variant="contained"
                disabled={!hasEnoughCoins}
                sx={{
                  backgroundColor: '#6200ea',
                  color: '#fff',
                  ':hover': {
                    backgroundColor: '#4e00b8',
                  },
                }}
                onClick={handleOpenDialog}
              >
                Resgatar item
              </Button>
            </Box>
          </Card>
        </Box>
      </div>

      <Dialog
        open={openDialog}
        onClose={handleCloseDialog}
        aria-labelledby="confirm-dialog-title"
        aria-describedby="confirm-dialog-description"
      >
        <DialogTitle id="confirm-dialog-title">Confirmar Resgate</DialogTitle>
        <DialogContent>
          <DialogContentText id="confirm-dialog-description">
            Tem certeza de que deseja resgatar "{vantagem.nome}" por {vantagem.valor} Moedas?
            Seu saldo após o resgate será {saldoMoedas - vantagem.valor} Moedas.
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleCloseDialog} color="secondary">
            Cancelar
          </Button>
          <Button onClick={handleResgatarItem} color="primary" autoFocus>
            Confirmar
          </Button>
        </DialogActions>
      </Dialog>

      <Snackbar
        open={openSnackbar}
        autoHideDuration={6000}
        onClose={handleCloseSnackbar}
        anchorOrigin={{ vertical: 'bottom', horizontal: 'center' }}
      >
        <Alert
          onClose={handleCloseSnackbar}
          severity={snackbarSeverity}
          sx={{ width: '100%' }}
        >
          {snackbarMessage}
        </Alert>
      </Snackbar>
    </div>
  );
}

export default DetailsVantagem;
