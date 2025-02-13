import DoarMoedas from './pages/doarMoedas/DoarMoedas';
import VizualizarAluno from './pages/crudAluno/vizualizarAluno';
import CadastrarAluno from './pages/crudAluno/cadastrarAluno';
import VizualizarEmpresa from './pages/crudEmpresa/vizualizarEmpresa';
import CadastrarEmpresa from './pages/crudEmpresa/cadastrarEmpresa';
import VizualizarVantagem from './pages/crudVantage/vizualizarVantage';
import Login from './pages/login/login';
import DadosAluno from './pages/crudAluno/detailsDadosAluno';
import DetalhesVantagem from './pages/crudVantage/detalheVantagemResgate';

import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';

function App() {
  return (
    <div className="App">
        <Router>
          <Routes>
            <Route path="/" element={<DoarMoedas />} />
            <Route path="/vizualizarAluno" element={<VizualizarAluno />} />
            <Route path="/cadastrarAluno" element={<CadastrarAluno />} />
            <Route path="/dadosAluno" element={<DadosAluno />} />
            <Route path="/vizualizarEmpresa" element={<VizualizarEmpresa />} />
            <Route path="/cadastrarEmpresa" element={<CadastrarEmpresa />} />
            <Route path="/vizualizarVantagem" element={<VizualizarVantagem />} />
            <Route path="/login" element={<Login />} />
            <Route path="/detalhesVantagem/:id" element={<DetalhesVantagem />} />
          </Routes>
        </Router>
    </div>
  );
}

export default App;