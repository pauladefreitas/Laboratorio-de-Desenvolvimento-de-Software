import React, { useState, useEffect } from 'react';
import Header from '../../components/Header';
import { IconButton, Card, CardContent, Typography, Grid, Modal, TextField, Button } from '@mui/material';
import { Edit, Delete } from '@mui/icons-material';
import axios from 'axios';
import './dadosAluno.css';

const DadosAluno = () => {
  const [aluno, setAluno] = useState(null);
  const [openModal, setOpenModal] = useState(false);
  const [editedAluno, setEditedAluno] = useState(null);

  useEffect(() => {
    const fetchAluno = async () => {
      try {
        const userId = localStorage.getItem('userId');
        const response = await axios.get(`http://localhost:8080/aluno/auth/${userId}`);
        setAluno(response.data);
      } catch (error) {
        console.error('Erro ao buscar dados do aluno:', error);
      }
    };

    fetchAluno();
  }, []);

  const handleDelete = async () => {
    try {
      const id = localStorage.getItem('userId');
      await axios.delete(`http://localhost:8080/aluno/${id}`);
      console.log('Aluno excluído com sucesso');
    } catch (error) {
      console.error('Erro ao excluir o aluno:', error);
    }
  };

  const handleOpenModal = () => {
    setEditedAluno(aluno); 
    setOpenModal(true);
  };

  const handleCloseModal = () => {
    setOpenModal(false);
  };

  const handleInputChange = (e) => {
    setEditedAluno({
      ...editedAluno,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmitEdit = async () => {
    try {
      const userId = localStorage.getItem('userId');

      await axios.put(`http://localhost:8080/aluno/${userId}`, editedAluno);
      console.log('Dados do aluno atualizados com sucesso');

      setAluno(editedAluno);
      setOpenModal(false);
    } catch (error) {
      console.error('Erro ao atualizar os dados do aluno:', error);
    }
  };

  if (!aluno) {
    return <div>Carregando...</div>;
  }

  return (
    <div>
      <Header />
      <h1 className='title'>Meus Dados</h1>
      <hr className='divider' />
      <div className='aluno-container'>
        <Card className='aluno-card'>
          <CardContent>
            <Grid container spacing={2}>
              <Grid item xs={12} sm={6}>
                <Typography variant='body1'><strong>Nome:</strong> {aluno.nome}</Typography>
                <Typography variant='body1'><strong>Email:</strong> {aluno.email}</Typography>
                <Typography variant='body1'><strong>Endereço:</strong> {aluno.endereco}</Typography>
                <Typography variant='body1'><strong>Curso:</strong> {aluno.curso}</Typography>
              </Grid>
              <Grid item xs={12} sm={6}>
                <Typography variant='body1'><strong>RG:</strong> {aluno.RG}</Typography>
                <Typography variant='body1'><strong>CPF:</strong> {aluno.CPF}</Typography>
                <Typography variant='body1'><strong>Saldo de Moedas:</strong> {aluno.saldoMoedas}</Typography>
              </Grid>
              <Grid item xs={12} className='aluno-actions'>
                <IconButton color='primary' aria-label='editar' onClick={handleOpenModal}>
                  <Edit />
                </IconButton>
                <IconButton color='secondary' aria-label='excluir' onClick={handleDelete}>
                  <Delete />
                </IconButton>
              </Grid>
            </Grid>
          </CardContent>
        </Card>

        {/* Modal de edição */}
        {openModal && editedAluno && (
          <Modal
            open={openModal}
            onClose={handleCloseModal}
            aria-labelledby='modal-editar-aluno'
            aria-describedby='modal-de-edicao-dos-dados-do-aluno'
          >
            <div className='modal-container'>
              <h2 id='modal-editar-aluno'>Editar Dados do Aluno</h2>
              <form className='form-editar-aluno'>
                {/* Campos de entrada */}
                <TextField
                  label='Nome'
                  name='nome'
                  value={editedAluno.nome}
                  onChange={handleInputChange}
                  fullWidth
                  margin='normal'
                />
                <TextField
                  label='Email'
                  name='email'
                  value={editedAluno.email}
                  onChange={handleInputChange}
                  fullWidth
                  margin='normal'
                />
                <TextField
                  label='Endereço'
                  name='endereco'
                  value={editedAluno.endereco}
                  onChange={handleInputChange}
                  fullWidth
                  margin='normal'
                />
                <TextField
                  label='Curso'
                  name='curso'
                  value={editedAluno.curso}
                  onChange={handleInputChange}
                  fullWidth
                  margin='normal'
                />
                <TextField
                  label='RG'
                  name='RG' // Nome atualizado
                  value={editedAluno.RG} // Nome atualizado
                  onChange={handleInputChange}
                  fullWidth
                  margin='normal'
                />
                <TextField
                  label='CPF'
                  name='CPF' // Nome atualizado
                  value={editedAluno.CPF} // Nome atualizado
                  onChange={handleInputChange}
                  fullWidth
                  margin='normal'
                />
                {/* Botões de ação */}
                <div className='modal-actions'>
                  <Button variant='contained' color='primary' onClick={handleSubmitEdit}>
                    Salvar
                  </Button>
                  <Button variant='outlined' onClick={handleCloseModal}>
                    Cancelar
                  </Button>
                </div>
              </form>
            </div>
          </Modal>
        )}
      </div>
    </div>
  );
};

export default DadosAluno;
