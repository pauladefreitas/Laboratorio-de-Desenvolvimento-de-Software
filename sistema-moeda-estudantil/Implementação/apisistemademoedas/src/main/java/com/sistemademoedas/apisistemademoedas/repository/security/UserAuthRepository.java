package com.sistemademoedas.apisistemademoedas.repository.security;

import com.sistemademoedas.apisistemademoedas.model.security.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    Optional<UserDetails> findByEmail(String email);
}
