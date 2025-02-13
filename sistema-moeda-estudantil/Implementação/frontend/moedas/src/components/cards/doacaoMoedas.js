import * as React from 'react';
import axios from 'axios';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import { TextField, Snackbar, Alert } from '@mui/material';

const BasicCard = () => {
  const [user, setUser] = React.useState('');
  const [coinAmount, setCoinAmount] = React.useState('');
  const [reason, setReason] = React.useState('');
  const [isButtonDisabled, setIsButtonDisabled] = React.useState(true);
  const [openSuccessSnackbar, setOpenSuccessSnackbar] = React.useState(false); 
  const [openErrorSnackbar, setOpenErrorSnackbar] = React.useState(false); 
  const [errorMessage, setErrorMessage] = React.useState(''); 

  React.useEffect(() => {
    setIsButtonDisabled(!(user && coinAmount && reason));
  }, [user, coinAmount, reason]);

  const handleDonate = async () => {
    try {
      const response = await axios.post(`http://localhost:8080/professor/87/transacao?id=87`, {
        moedas: parseInt(coinAmount),
        idAluno: parseInt(user),
        descricao: reason
      });
      console.log('Doação realizada com sucesso:', response.data);
      setOpenSuccessSnackbar(true); 
    } catch (error) {
      const message = 
        error.response && typeof error.response.data === 'string' && error.response.data.includes("saldo insuficiente")
          ? "Saldo insuficiente!"
          : "Erro ao realizar a doação!";
      setErrorMessage(message);
      setOpenErrorSnackbar(true); 
    }
  };

  const handleCloseSnackbar = () => {
    setOpenSuccessSnackbar(false);
    setOpenErrorSnackbar(false);
    if (openSuccessSnackbar) {
      window.location.reload(); 
    }
  };

  return (
    <Card sx={{ minWidth: 275 }}>
      <CardContent sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
        <Typography gutterBottom sx={{ color: 'text.secondary', fontSize: 20 }}>
          Doar Moedas
        </Typography>

        <Typography sx={{ color: 'text.secondary', mb: 1, fontSize: 14 }}>
          Deseja reconhecer algum aluno? Transfira algumas moedas para ele
        </Typography>

        <TextField 
          id="user" 
          label="Usuário (ID)" 
          variant="standard" 
          value={user} 
          onChange={(e) => setUser(e.target.value)} 
        />
        <TextField 
          id="coin-amount" 
          label="Quantidade Moedas" 
          variant="standard" 
          type="number" 
          value={coinAmount} 
          onChange={(e) => setCoinAmount(e.target.value)} 
        />
        <TextField 
          id="reason" 
          label="Motivo" 
          variant="standard" 
          value={reason} 
          onChange={(e) => setReason(e.target.value)} 
        />
      </CardContent>

      <CardActions>
        <Button 
          variant="contained" 
          disabled={isButtonDisabled} 
          onClick={handleDonate}
        >
          DOAR
        </Button>
      </CardActions>

      <Snackbar
        open={openSuccessSnackbar}
        onClose={handleCloseSnackbar}
        anchorOrigin={{ vertical: 'top', horizontal: 'center' }}
      >
        <Alert onClose={handleCloseSnackbar} severity="success" sx={{ width: '100%' }}>
          Doação realizada com sucesso!
        </Alert>
      </Snackbar>

      <Snackbar
        open={openErrorSnackbar}
        onClose={handleCloseSnackbar}
        anchorOrigin={{ vertical: 'top', horizontal: 'center' }}
      >
        <Alert onClose={handleCloseSnackbar} severity="error" sx={{ width: '100%' }}>
          {errorMessage}
        </Alert>
      </Snackbar>
    </Card>
  );
}

export default BasicCard;
