package com.sistemademoedas.apisistemademoedas.controller.security;

import com.sistemademoedas.apisistemademoedas.model.dto.request.AuthRequestDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.response.LoginResponseDTO;
import com.sistemademoedas.apisistemademoedas.model.security.UserAuth;
import com.sistemademoedas.apisistemademoedas.service.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthRequestDTO authRequestDTO) {
        var authToken = new UsernamePasswordAuthenticationToken(authRequestDTO.email(), authRequestDTO.senha());
        var authentication = authenticationManager.authenticate(authToken);

        var user = (UserAuth) authentication.getPrincipal();
        var tokenJWT = tokenService.generateToken(user);

        return ResponseEntity.ok().body(new LoginResponseDTO(user.getId(), user.getEmail(), tokenJWT, user.getRole()));
    }
}

