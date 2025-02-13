import * as React from 'react';
import Header from '../../components/Header';
import CardQtdMoeda from '../../components/cards/quantidadeMoedas';
import DoacaoMoeda from '../../components/cards/doacaoMoedas';
import DoacoesRealizadas from '../../components/cards/doacoesRealizadas';
import logo from '../../images/iconMoeda.png';
import Backdrop from '@mui/material/Backdrop';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Modal from '@mui/material/Modal';
import Fade from '@mui/material/Fade';
import Typography from '@mui/material/Typography';
import './doarMoedas.css';
import axios from 'axios';

const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    border: 'none',
    borderRadius: '5px',
    boxShadow: 24,
};

function DoarMoedas() {
    const [open, setOpen] = React.useState(false);
    const [transactions, setTransactions] = React.useState([]);

    const handleOpen = async () => {
        setOpen(true);
        try {
            const response = await axios.get('http://localhost:8080/gerenciador_moedas/professor/87');
            setTransactions(response.data);
        } catch (error) {
            console.error('Erro ao buscar transações:', error);
        }
    };

    const handleClose = () => {
        setOpen(false);
        setTransactions([]); 
    };

    return (
        <div>
            <Header />
            <div className='conteudo'>
                <div className='conteudoPage'>
                    <div className='logoPage'>
                        <img src={logo} alt="Logo Moeda" />
                        <h1>Doar moedas</h1>
                    </div>

                    <Button onClick={handleOpen} variant="contained" sx={{ background: '#191970' }} style={{ width: '300px' }}>
                        Ver minhas doações
                    </Button>

                    <Modal
                        aria-labelledby="transition-modal-title"
                        aria-describedby="transition-modal-description"
                        open={open}
                        onClose={handleClose}
                        closeAfterTransition
                        BackdropComponent={Backdrop}
                        BackdropProps={{
                            timeout: 500,
                        }}
                    >
                        <Fade in={open}>
                            <Box sx={style}>
                                <Typography sx={{
                                    background: '#DAA520',
                                    color: '#fff',
                                    textAlign: 'center',
                                }} id="transition-modal-title" variant="h6" component="h2">
                                    Doações Realizadas
                                </Typography>


                                <div className='cardsRealizadas'>
                                    {transactions.map((transaction) => (
                                        <DoacoesRealizadas key={transaction.id} transaction={transaction} />
                                    ))}
                                </div>
                            </Box>
                        </Fade>
                    </Modal>
                </div>

                <div className='cards'>
                    <CardQtdMoeda />
                    <DoacaoMoeda />
                </div>
            </div>
        </div>
    );
}

export default DoarMoedas;
