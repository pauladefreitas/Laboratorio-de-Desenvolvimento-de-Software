import React from 'react';
import { useNavigate } from 'react-router-dom';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Menu from '@mui/material/Menu';
import MenuIcon from '@mui/icons-material/Menu';
import Container from '@mui/material/Container';
import Button from '@mui/material/Button';
import MenuItem from '@mui/material/MenuItem';
import ExitToAppIcon from '@mui/icons-material/ExitToApp';
import Logo from '../images/porco.png';

const allPages = {
  ALUNO: ['Aluno', 'Vantagem'],
  EMPRESA: ['Empresa'], 
  DEFAULT: ['Doar Moedas', 'Cadastro', 'Login'],
};

const ResponsiveAppBar = () => {
  const [anchorElNav, setAnchorElNav] = React.useState(null);
  const [anchorElCadastro, setAnchorElCadastro] = React.useState(null);
  const navigate = useNavigate();

  const role = localStorage.getItem('role') || 'DEFAULT';
  const pages = allPages[role] || allPages.DEFAULT;

  const handleOpenNavMenu = (event) => {
    setAnchorElNav(event.currentTarget);
  };

  const handleOpenCadastroMenu = (event) => {
    setAnchorElCadastro(event.currentTarget);
  };

  const handleCloseNavMenu = (page) => {
    setAnchorElNav(null);
    if (page === 'Aluno') navigate('/vizualizarAluno');
    if (page === 'Doar Moedas') navigate('/');
    if (page === 'Empresa') navigate('/vizualizarEmpresa');
    if (page === 'Login') navigate('/login');
    if (page === 'Vantagem') navigate('/vizualizarVantagem');
  };

  const handleCloseCadastroMenu = () => {
    setAnchorElCadastro(null);
  };

  const handleNavigateCadastro = (route) => {
    navigate(route);
    handleCloseCadastroMenu();
  };

  const handleLogout = () => {
    localStorage.clear();
    navigate('/login');
  };

  return (
    <AppBar position="static">
      <Container maxWidth="xl" sx={{ background: '#191970' }}>
        <Toolbar disableGutters>
          <Box
            component="img"
            src={Logo}
            alt="Logo"
            sx={{ display: { xs: 'none', md: 'flex' }, width: 50, height: 50 }}
          />
          <Typography
            variant="h6"
            noWrap
            component="a"
            href="#"
            sx={{
              mr: 2,
              display: { xs: 'none', md: 'flex' },
              fontFamily: 'monospace',
              fontWeight: 700,
              letterSpacing: '.1rem',
              color: 'inherit',
              textDecoration: 'none',
            }}
          >
            chiCOIN
          </Typography>

          <Box sx={{ flexGrow: 1, display: { xs: 'flex', md: 'none' } }}>
            <IconButton
              size="large"
              aria-label="account of current user"
              aria-controls="menu-appbar"
              aria-haspopup="true"
              onClick={handleOpenNavMenu}
              color="inherit"
            >
              <MenuIcon />
            </IconButton>
            <Menu
              id="menu-appbar"
              anchorEl={anchorElNav}
              anchorOrigin={{
                vertical: 'bottom',
                horizontal: 'left',
              }}
              keepMounted
              transformOrigin={{
                vertical: 'top',
                horizontal: 'left',
              }}
              open={Boolean(anchorElNav)}
              onClose={() => setAnchorElNav(null)}
              sx={{ display: { xs: 'block', md: 'none' } }}
            >
              {pages.map((page) =>
                page === 'Cadastro' ? (
                  <MenuItem
                    key="Cadastro"
                    onClick={handleOpenCadastroMenu}
                  >
                    <Typography sx={{ textAlign: 'center' }}>Cadastro</Typography>
                  </MenuItem>
                ) : (
                  <MenuItem key={page} onClick={() => handleCloseNavMenu(page)}>
                    <Typography sx={{ textAlign: 'center' }}>{page}</Typography>
                  </MenuItem>
                )
              )}
            </Menu>
          </Box>
          <Box
            component="img"
            src={Logo}
            alt="Logo"
            sx={{ display: { xs: 'flex', md: 'none' }, mr: 1, width: 30, height: 30 }}
          />
          <Typography
            variant="h5"
            noWrap
            component="a"
            href="#"
            sx={{
              mr: 2,
              display: { xs: 'flex', md: 'none' },
              flexGrow: 1,
              fontFamily: 'monospace',
              fontWeight: 700,
              letterSpacing: '.3rem',
              color: 'inherit',
              textDecoration: 'none',
            }}
          >
            LOGO
          </Typography>
          <Box sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' } }}>
            {pages.map((page) =>
              page === 'Cadastro' ? (
                <Button
                  key="Cadastro"
                  onClick={handleOpenCadastroMenu}
                  sx={{ my: 2, color: 'white', display: 'block' }}
                >
                  Cadastro
                </Button>
              ) : (
                <Button
                  key={page}
                  onClick={() => handleCloseNavMenu(page)}
                  sx={{ my: 2, color: 'white', display: 'block' }}
                >
                  {page}
                </Button>
              )
            )}
          </Box>
          <Menu
            id="menu-cadastro"
            anchorEl={anchorElCadastro}
            anchorOrigin={{
              vertical: 'top',
              horizontal: 'right',
            }}
            keepMounted
            transformOrigin={{
              vertical: 'top',
              horizontal: 'right',
            }}
            open={Boolean(anchorElCadastro)}
            onClose={handleCloseCadastroMenu}
          >
            <MenuItem onClick={() => handleNavigateCadastro('/cadastrarAluno')}>
              Cadastrar Aluno
            </MenuItem>
            <MenuItem onClick={() => handleNavigateCadastro('/cadastrarEmpresa')}>
              Cadastrar Empresa
            </MenuItem>
          </Menu>
          {(role === 'ALUNO' || role === 'EMPRESA') && (
            <IconButton color="inherit" onClick={handleLogout}>
              <ExitToAppIcon />
            </IconButton>
          )}
        </Toolbar>
      </Container>
    </AppBar>
  );
};

export default ResponsiveAppBar;
