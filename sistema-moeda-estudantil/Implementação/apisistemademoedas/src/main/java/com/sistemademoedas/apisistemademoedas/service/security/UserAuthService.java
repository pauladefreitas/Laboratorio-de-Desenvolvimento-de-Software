package com.sistemademoedas.apisistemademoedas.service.security;

import com.sistemademoedas.apisistemademoedas.model.security.UserAuth;
import com.sistemademoedas.apisistemademoedas.repository.security.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserDetailsService {

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userAuthRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado. Email " + email));    }

    public void create(UserAuth userAuth) {
        userAuthRepository.save(userAuth);
    }
}
