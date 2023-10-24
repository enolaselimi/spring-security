package com.joan.security.service;

import com.joan.security.dto.SignUpDTO;
import com.joan.security.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    private AuthenticationService authenticationService;

    public UserDTO signUp(SignUpDTO signUpDTO) {
        return new UserDTO(1L, signUpDTO.getFirstName(), signUpDTO.getLastName(), signUpDTO.getLogin(), "token", Collections.EMPTY_LIST);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authenticationService.findByLogin(username);
    }
}
