package com.joan.security.service;

import com.joan.security.dto.CredentialsDTO;
import com.joan.security.dto.UserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.List;

@Service
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO authenticate(CredentialsDTO credentialsDto) {
        String encodedMasterPassword = passwordEncoder.encode(CharBuffer.wrap("the-password"));
        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), encodedMasterPassword)) {
            return this.findByLogin(credentialsDto.getLogin());
        }
        throw new RuntimeException("Invalid password");
    }

    public UserDTO findByLogin(String login) {
        if ("joan".equals(login)) {
            return new UserDTO(1L, "Joan", "Janku", "joan", "token", List.of("ROLE_VIEWER", "ROLE_EDITOR"));
        }
        if ("john".equals(login)) {
            return new UserDTO(1L, "John", "Doe", "john", "token", List.of("ROLE_VIEWER"));
        }
        throw new RuntimeException("Invalid login");
    }
}
