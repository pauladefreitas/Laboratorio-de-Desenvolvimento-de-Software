import { Button, Dialog, DialogActions, DialogContent, DialogTitle, TextField } from "@mui/material";
import axios from "axios";
import { useState } from "react";

const ModalAddVantagem = ({ open, onClose, empresaId }) => {
    const [vantagemData, setVantagemData] = useState({
        nome: '',
        descricao: '',
        valor: 0,
        fotoUrl: '',
    });

    const handleVantagemInputChange = (e) => {
        const { name, value } = e.target;
        setVantagemData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    const handleVantagemSubmit = async () => {
        try {
            const response = await axios.post(
                `http://localhost:8080/empresas_parceiras/${empresaId}/vantagens?id=${empresaId}`, 
                vantagemData,
                {
                    headers: {
                        'Content-Type': 'application/json',
                    },
                }

            );
            alert("Vantagem cadastrada com sucesso!");
            console.log("Resposta do servidor:", response.data);
            onClose(); 
        } catch (error) {
            console.error("Erro ao cadastrar vantagem:", error);
            if (error.response) {
                console.error("Resposta do servidor:", error.response.data);
                alert(`Erro: ${error.response.data.message || "Verifique os dados enviados."}`);
            } else {
                alert("Erro desconhecido ao cadastrar vantagem.");
            }
        }
    };

    console.log("🚀 ~ ModalAddVantagem ~ vantagemData.fotoUrl:", vantagemData.fotoUrl)

    

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Cadastre Vantagem</DialogTitle>
            <DialogContent>
                <TextField
                    fullWidth
                    label="Nome da Vantagem"
                    name="nome"
                    value={vantagemData.nome}
                    onChange={handleVantagemInputChange}
                    margin="dense"
                />
                <TextField
                    fullWidth
                    label="Descrição da Vantagem"
                    name="descricao"
                    value={vantagemData.descricao}
                    onChange={handleVantagemInputChange}
                    margin="dense"
                />
                <TextField
                    fullWidth
                    label="Valor"
                    name="valor"
                    value={vantagemData.valor}
                    onChange={handleVantagemInputChange}
                    margin="dense"
                    type="number"
                />
                 <TextField
                    fullWidth
                    label="Url Foto"
                    name="fotoUrl"
                    value={vantagemData.fotoUrl}
                    onChange={handleVantagemInputChange}
                    margin="dense"
                />
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose} color="primary">
                    Cancelar
                </Button>
                <Button onClick={handleVantagemSubmit} color="primary">
                    Cadastrar
                </Button>
            </DialogActions>
        </Dialog>
    );
};

export default ModalAddVantagem;
