package com.api.usersproject.services;

import com.api.usersproject.models.UserModel;
import com.api.usersproject.repositores.UserRepository;
import jakarta.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserServices {

    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserModel save(UserModel userModel) {
        // CRIPTOGRAFIA DA SENHA
        String RAW_PASSWORD = userModel.getPassword();
        String ENC_PASSWORD = BCrypt.hashpw(RAW_PASSWORD, BCrypt.gensalt());
        userModel.setPassword(ENC_PASSWORD);
        return userRepository.save(userModel);
    }

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserModel> findByID(UUID id) {
        return userRepository.findById(id);
    }


    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

}
