package com.joan.security.service;

import com.joan.security.dto.CredentialsDTO;
import com.joan.security.dto.User;
import com.joan.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User authenticate(CredentialsDTO credentialsDto) {
        User user = userRepository.findUserDTOByLogin(credentialsDto.getLogin());
        String encodedMasterPassword = passwordEncoder.encode(CharBuffer.wrap(user.getPassword()));
        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), encodedMasterPassword)) {
            return this.findByLogin(credentialsDto.getLogin());
        }
        throw new RuntimeException("Invalid password");
    }

    public User findByLogin(String login) {
        User user = userRepository.findUserDTOByLogin(login);
        if(user == null){
            throw new RuntimeException("Invalid info");
        }else{
            return user;
        }
    }
}
