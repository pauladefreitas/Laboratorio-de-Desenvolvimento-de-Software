import React, { useEffect, useState } from 'react';
import Header from '../../components/Header';
import CardVantagem from '../../components/cards/cardVantegem';
import './vizualizarVantagem.css';
import StoreIcon from '@mui/icons-material/Store';
import RedeemIcon from '@mui/icons-material/Redeem';
import axios from 'axios';
import { Button, Modal, Box, Typography, Divider } from '@mui/material';
import VerMoedasDoadas from '../../components/cards/verMoedasDoadas';

function VizualizarVantagem() {
  const [vantagens, setVantagens] = useState([]);
  const [openModal, setOpenModal] = useState(false);
  const [resgates, setResgates] = useState([]);
  const [openMoedasModal, setOpenMoedasModal] = useState(false);
  const [moedasRecebidas, setMoedasRecebidas] = useState([]);

  useEffect(() => {
    axios
      .get('http://localhost:8080/vantagens')
      .then((response) => {
        setVantagens(response.data);
      })
      .catch((error) => {
        console.error('Erro ao buscar lista vantagens:', error);
      });
  }, []);

  const handleOpenModal = () => {
    setOpenModal(true);
    const userId = localStorage.getItem('userId');
    axios
      .get(`http://localhost:8080/gerenciador_vantagens/${userId}`)
      .then((response) => {
        setResgates(response.data);
      })
      .catch((error) => {
        console.error('Erro ao buscar resgates:', error);
      });
  };

  const handleCloseModal = () => {
    setOpenModal(false);
  };

  const handleOpenMoedasModal = () => {
    setOpenMoedasModal(true);
    const userAuthId = localStorage.getItem('userId');
    axios
      .get(`http://localhost:8080/gerenciador_moedas/aluno/${userAuthId}`)
      .then((response) => {
        setMoedasRecebidas(response.data);
      })
      .catch((error) => {
        console.error('Erro ao buscar moedas recebidas:', error);
      });
  };

  const handleCloseMoedasModal = () => {
    setOpenMoedasModal(false);
  };

  const modalStyle = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: '60%',
    bgcolor: 'background.paper',
    border: '1px solid #000',
    boxShadow: 24,
    p: 4,
  };

  return (
    <div>
      <Header />
      <div className='lojaVantagem'>
        <div className='lojaVantagemLeft'>
          <StoreIcon sx={{ color: '#191970', fontSize: '45px' }} />
          <Typography variant='h4' component='h1' className='storeC'>
            Loja chiCOIN
          </Typography>
        </div>
        <div className='buttonGroup'>
          <Button
            variant='outlined'
            startIcon={<RedeemIcon />}
            onClick={handleOpenModal}
            sx={{
              boxShadow: 'none',
              borderColor: 'grey',
              backgroundColor: 'transparent',
              color: 'inherit',
            }}
          >
            Meus Resgates
          </Button>
          <Button
            variant='outlined'
            startIcon={<RedeemIcon />}
            onClick={handleOpenMoedasModal}
            sx={{
              boxShadow: 'none',
              borderColor: 'grey',
              backgroundColor: 'transparent',
              color: 'inherit',
              marginLeft: '5px',
            }}
          >
            Ver Moedas Recebidas
          </Button>
        </div>
      </div>
      <hr className='hrV' />
      <div className='listVantagem'>
        {vantagens.map((vantagem) => (
          <CardVantagem
            key={vantagem.id}
            id={vantagem.id}
            nome={vantagem.nome}
            descricao={vantagem.descricao}
            valor={vantagem.valor}
            fotoUrl={vantagem.fotoUrl}
          />
        ))}
      </div>
      <Modal
        open={openModal}
        onClose={handleCloseModal}
        aria-labelledby='modal-title'
        aria-describedby='modal-description'
      >
        <Box sx={modalStyle}>
          <h2 id='modal-title'>Meus Resgates</h2>
          {resgates.length > 0 ? (
            resgates.map((resgate, index) => (
              <div key={resgate.id}>
                <p><strong>- {resgate.valor} moedas</strong></p>
                <p>Descrição: {resgate.descricao}</p>
                {index < resgates.length - 1 && <Divider sx={{ my: 2 }} />} 
              </div>
            ))
          ) : (
            <p>Nenhum resgate encontrado.</p>
          )}
        </Box>
      </Modal>
      <Modal
        open={openMoedasModal}
        onClose={handleCloseMoedasModal}
        aria-labelledby='modal-moedas-title'
        aria-describedby='modal-moedas-description'
      >
        <Box sx={modalStyle}>
          <h2 id='modal-moedas-title'>Moedas Recebidas</h2>
          {moedasRecebidas.length > 0 ? (
            moedasRecebidas.map((doacao) => (
              <VerMoedasDoadas key={doacao.id} doacao={doacao} />
            ))
          ) : (
            <p>Nenhuma moeda recebida encontrada.</p>
          )}
        </Box>
      </Modal>
    </div>
  );
}

export default VizualizarVantagem;
