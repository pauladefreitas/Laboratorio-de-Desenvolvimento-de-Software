import React, { useState } from "react";
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    IconButton,
    Tooltip,
    TextField
} from "@mui/material";
import { GridDeleteIcon } from "@mui/x-data-grid";
import EditIcon from '@mui/icons-material/Edit';
import axios from "axios";

const ModalVerVantagem = ({ open, onClose, vantagens, onDeleteSuccess }) => {
    const [editModalOpen, setEditModalOpen] = useState(false);
    const [selectedVantagem, setSelectedVantagem] = useState(null);
    const [editedVantagem, setEditedVantagem] = useState({
        nome: '',
        descricao: '',
        valor: ''
    });

    const handleEditClick = (vantagem) => {
        setSelectedVantagem(vantagem);
        setEditedVantagem({
            nome: vantagem.nome,
            descricao: vantagem.descricao,
            valor: vantagem.valor,
        });
        setEditModalOpen(true);
    };

    const handleSaveEdit = async () => {
        try {
            await axios.put(`http://localhost:8080/vantagens/${selectedVantagem.id}`, editedVantagem);
            alert("Vantagem editada com sucesso!");
            onDeleteSuccess(); 
            setEditModalOpen(false);
        } catch (error) {
            console.error("Erro ao editar a vantagem:", error);
            alert("Erro ao editar a vantagem.");
        }
    };

    const handleDelete = async (id) => {
        try {
            await axios.delete(`http://localhost:8080/vantagens/${id}`);
            alert("Vantagem deletada com sucesso!");
            onDeleteSuccess();
        } catch (error) {
            console.error("Erro ao deletar a vantagem:", error);
            alert("Erro ao deletar a vantagem.");
        }
    };

    return (
        <>
            <Dialog open={open} onClose={onClose} fullWidth>
                <DialogTitle>Vantagens Cadastradas</DialogTitle>
                <DialogContent>
                    {vantagens.length > 0 ? (
                        vantagens.map((vantagem) => (
                            <div
                                key={vantagem.id}
                                style={{
                                    display: "flex",
                                    flexDirection: "column",
                                    justifyContent: "space-between",
                                    alignItems: "center",
                                    marginBottom: "1rem",
                                    padding: "0.5rem",
                                    border: "1px solid #ddd",
                                    borderRadius: "5px",
                                }}
                            >
                                <div>
                                    <p><strong>Nome:</strong> {vantagem.nome}</p>
                                    <p><strong>Descrição:</strong> {vantagem.descricao}</p>
                                    <p><strong>Valor:</strong> R$ {vantagem.valor}</p>
                                </div>

                                <div>
                                    <IconButton
                                        onClick={() => handleDelete(vantagem.id)}
                                        style={{ color: "red" }}
                                        aria-label="Deletar Vantagem"
                                    >
                                        <GridDeleteIcon />
                                    </IconButton>

                                    <Tooltip title="Editar Vantagem">
                                        <IconButton sx={{ color: '#191970' }} onClick={() => handleEditClick(vantagem)}>
                                            <EditIcon />
                                        </IconButton>
                                    </Tooltip>
                                </div>
                            </div>
                        ))
                    ) : (
                        <p>Nenhuma vantagem encontrada.</p>
                    )}
                </DialogContent>
                <DialogActions>
                    <Button onClick={onClose} color="primary">
                        Fechar
                    </Button>
                </DialogActions>
            </Dialog>

            <Dialog open={editModalOpen} onClose={() => setEditModalOpen(false)} fullWidth>
                <DialogTitle>Editar Vantagem</DialogTitle>
                <DialogContent>
                    <TextField
                        fullWidth
                        label="Nome"
                        value={editedVantagem.nome}
                        onChange={(e) => setEditedVantagem({ ...editedVantagem, nome: e.target.value })}
                        margin="normal"
                    />
                    <TextField
                        fullWidth
                        label="Descrição"
                        value={editedVantagem.descricao}
                        onChange={(e) => setEditedVantagem({ ...editedVantagem, descricao: e.target.value })}
                        margin="normal"
                    />
                    <TextField
                        fullWidth
                        label="Valor"
                        type="number"
                        value={editedVantagem.valor}
                        onChange={(e) => setEditedVantagem({ ...editedVantagem, valor: e.target.value })}
                        margin="normal"
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={() => setEditModalOpen(false)} color="primary">Cancelar</Button>
                    <Button onClick={handleSaveEdit} color="primary">Salvar</Button>
                </DialogActions>
            </Dialog>
        </>
    );
};

export default ModalVerVantagem;
