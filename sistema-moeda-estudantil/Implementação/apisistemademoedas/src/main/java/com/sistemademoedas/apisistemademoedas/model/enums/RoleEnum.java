package com.sistemademoedas.apisistemademoedas.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Getter
public enum RoleEnum {
    ALUNO(1, "ROLE_ALUNO") {
        @Override
        public Collection<? extends GrantedAuthority> getSimpleGrantedAuthority() {
            return List.of(new SimpleGrantedAuthority("ROLE_ALUNO"));
        }
    },
    PROFESSOR(2, "ROLE_PROFESSOR") {
        @Override
        public Collection<? extends GrantedAuthority> getSimpleGrantedAuthority() {
            return List.of(new SimpleGrantedAuthority("ROLE_PROFESSOR"));
        }
    },
    EMPRESA(3, "ROLE_EMPRESA_PARCEIRA") {
        @Override
        public Collection<? extends GrantedAuthority> getSimpleGrantedAuthority() {
            return List.of(new SimpleGrantedAuthority("ROLE_EMPRESA_PARCEIRA"));
        }
    },
    INSTITUICAO(4, "ROLE_INSTITUICAO") {
        @Override
        public Collection<? extends GrantedAuthority> getSimpleGrantedAuthority() {
            return List.of(new SimpleGrantedAuthority("ROLE_INSTITUICAO"));
        }
    };

    private Integer code;
    private String description;

    //retorna o enum
    public static RoleEnum toEnum(Integer code) {
        if (Objects.isNull(code))
            return null;

        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (code.equals(roleEnum.code)) {
                return roleEnum;
            }
        }

        throw new IllegalArgumentException("Code inválido " + RoleEnum.class.getName() + "." + code);
    }

    public abstract Collection<? extends GrantedAuthority> getSimpleGrantedAuthority();

}