package com.example.Othellodifficult.service;

import com.example.Othellodifficult.dto.user.UserRequest;
import com.example.Othellodifficult.entity.UserEntity;
import com.example.Othellodifficult.mapper.UserMapper;
import com.example.Othellodifficult.repository.UserRepository;
import com.example.Othellodifficult.token.TokenHandler;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void signUp(UserRequest signUpRequest){
        if (userRepository.existsByUsername(signUpRequest.getUsername())){
            throw new RuntimeException("Username already exists!!!");
        }
        signUpRequest.setPassword(BCrypt.hashpw(signUpRequest.getPassword(), BCrypt.gensalt()));
        userRepository.save(UserMapper.getEntityFromRequest(signUpRequest));
    }

    public String logIn(UserRequest logInRequest){
        UserEntity userEntity = userRepository.findByUsername(logInRequest.getUsername());
        String currentHashedPassword = userEntity.getPassword();
        if (BCrypt.checkpw(logInRequest.getPassword(), currentHashedPassword)){
            return TokenHandler.generateToken(userEntity);
        }
        throw new RuntimeException("Incorrect password!!!");
    }

}
