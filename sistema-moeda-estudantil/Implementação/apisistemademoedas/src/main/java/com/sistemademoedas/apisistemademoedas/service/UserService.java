package com.sistemademoedas.apisistemademoedas.service;

import com.sistemademoedas.apisistemademoedas.exception.UserNotFoundException;
import com.sistemademoedas.apisistemademoedas.model.User;
import com.sistemademoedas.apisistemademoedas.model.dto.request.UserRequestDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.response.UserResponseDTO;
import com.sistemademoedas.apisistemademoedas.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
        @Autowired
        private UserRepository userRepository;


        public User findByID(Long id){
            Optional<User> user = userRepository.findById(id);
            return user.orElseThrow(() -> new RuntimeException("User não encontrado. Id" + id));
        }

        @Transactional
        public User create(User user){
            user.setId(null);
            user = this.userRepository.save(user);
            return user;
        }

        public List<UserResponseDTO> getAll() {
            return userRepository.findAll()
                    .stream()
                    .map(UserResponseDTO::fromEntity)
                    .toList();
        }

        public UserResponseDTO update(Long id, UserRequestDTO userRequestDTO) {
            var user = userRepository.findById(id)
                    .orElseThrow(() -> new UserNotFoundException("User não encontrado. Id " + id));
            user.update(userRequestDTO);
            userRepository.save(user);
            return UserResponseDTO.fromEntity(user);
        }

        public void delete(Long id){
            userRepository.findById(id);
            try {
                userRepository.deleteById(id);
            } catch (Exception e) {
                throw new RuntimeException("Não é possivel excluir um User.");
            }
        }
}
