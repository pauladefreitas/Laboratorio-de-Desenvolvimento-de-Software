import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Header from '../../components/Header';
import VerMoedasDoadas from '../../components/cards/verMoedasDoadas';
import {
    Button, IconButton, Snackbar, Alert, Dialog,
    DialogActions, DialogContent, DialogTitle
} from '@mui/material';
import { DataGrid } from '@mui/x-data-grid';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import RemoveRedEyeIcon from '@mui/icons-material/RemoveRedEye';
import { useNavigate } from 'react-router-dom';
import Chico from '../../images/chicoM.png'

const VizualizarAluno = () => {
    const [alunos, setAlunos] = useState([]);
    const [doacoes, setDoacoes] = useState([]); 
    const [snackbarOpen, setSnackbarOpen] = useState(false);
    const [snackbarMessage, setSnackbarMessage] = useState('');
    const [snackbarSeverity, setSnackbarSeverity] = useState('success');
    const [deleteDialogOpen, setDeleteDialogOpen] = useState(false);
    const [alunoToDelete, setAlunoToDelete] = useState(null);
    const [verMoedasOpen, setVerMoedasOpen] = useState(false);
    const [selectedAlunoId, setSelectedAlunoId] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchAlunos = async () => {
            try {
                const response = await axios.get('http://localhost:8080/aluno');
                setAlunos(response.data);
            } catch (error) {
                console.error('Erro ao carregar alunos:', error);
            }
        };

        fetchAlunos();
    }, []);

    const handleDeleteClick = (id) => {
        setAlunoToDelete(id);
        setDeleteDialogOpen(true);
    };

    const confirmDelete = async () => {
        try {
            await axios.delete(`http://localhost:8080/aluno/${alunoToDelete}`);
            setAlunos(alunos.filter((aluno) => aluno.id !== alunoToDelete));
            setSnackbarMessage('Aluno deletado com sucesso!');
            setSnackbarSeverity('success');
        } catch (error) {
            console.error('Erro ao deletar aluno:', error);
            setSnackbarMessage('Erro ao deletar o aluno.');
            setSnackbarSeverity('error');
        }
        setSnackbarOpen(true);
        setDeleteDialogOpen(false);
        setAlunoToDelete(null);
    };

    const handleSnackbarClose = () => {
        setSnackbarOpen(false);
    };

    const handleDialogClose = () => {
        setDeleteDialogOpen(false);
        setAlunoToDelete(null);
    };

    const handleEdit = (id) => {
        console.log(`Edit student with id: ${id}`);
    };

    const handleVerMoedasClose = () => {
        setVerMoedasOpen(false);
        setDoacoes([]);
    };

    const columns = [
        { field: 'id', headerName: 'ID', width: 10 },
        { field: 'nome', headerName: 'Nome', width: 90 },
        { field: 'email', headerName: 'Email', width: 150 },
        { field: 'endereco', headerName: 'Endereço', width: 150 },
        { field: 'saldoMoedas', headerName: 'Moedas', width: 70 },
        { field: 'curso', headerName: 'Curso', width: 150 },
        { field: 'RG', headerName: 'RG', width: 100 },
        { field: 'CPF', headerName: 'CPF', width: 150 },
        {
            field: 'instituicaoEnsino',
            headerName: 'Instituição de Ensino',
            width: 150,
            valueGetter: (params) => params.row?.instituicaoEnsino?.id || ''
        },
        {
            field: 'actions',
            headerName: 'Ações',
            width: 150,
            sortable: false,
            renderCell: (params) => (
                <>
                    <IconButton color="primary" onClick={() => handleEdit(params.row.id)}>
                        <EditIcon />
                    </IconButton>
                    <IconButton color="secondary" onClick={() => handleDeleteClick(params.row.id)}>
                        <DeleteIcon />
                    </IconButton>
                </>
            )
        }
    ];

    return (
        <div>
            <Header />

            <div className='listEstudante'>
                <h1>Lista de Alunos</h1>
                
            </div>

            <hr className='divider' />

            <div className="tableContainer">
                <div style={{ height: 400, width: '95%' }}>
                    <DataGrid
                        rows={alunos}
                        columns={columns}
                        pageSize={5}
                        rowsPerPageOptions={[5]}
                        disableSelectionOnClick
                        hideFooterSelectedRowCount
                        sx={{
                            '& .MuiDataGrid-columnHeaders': {
                                backgroundColor: '#f5f5f5',
                                color: '#333',
                            },
                            '& .MuiDataGrid-cell': {
                                backgroundColor: '#fff',
                                color: '#333',
                            },
                        }}
                    />
                </div>
            </div>

            <Snackbar open={snackbarOpen} autoHideDuration={6000} onClose={handleSnackbarClose}>
                <Alert onClose={handleSnackbarClose} severity={snackbarSeverity} sx={{ width: '100%' }}>
                    {snackbarMessage}
                </Alert>
            </Snackbar>

            <Dialog open={deleteDialogOpen} onClose={handleDialogClose}>
                <DialogTitle>Confirmar Exclusão</DialogTitle>
                <DialogActions>
                    <Button onClick={handleDialogClose} color="primary">Cancelar</Button>
                    <Button onClick={confirmDelete} color="secondary" autoFocus>Deletar</Button>
                </DialogActions>
            </Dialog>

            <Dialog open={verMoedasOpen} onClose={handleVerMoedasClose} fullWidth maxWidth="sm">
                <div  style={{ display: 'flex', alignItems: 'center', flexDirection:'column', }}>
                    <h1>Veja sua moedas recebidas</h1>
                    {/* <img src={Chico} style={{ width: '120px', textAlign: 'center' }}></img> */}
                </div>
                <DialogContent >
                    
                    {doacoes.map((doacao) => (
                        <VerMoedasDoadas key={doacao.id} doacao={doacao} />
                    ))}
                </DialogContent>
            </Dialog>
        </div>
    );
};

export default VizualizarAluno;
