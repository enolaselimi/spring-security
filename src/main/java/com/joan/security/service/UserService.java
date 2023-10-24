package com.joan.security.service;

import com.joan.security.dto.SignUpDTO;
import com.joan.security.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

@Service
public class UserService {

    public UserDTO signUp(SignUpDTO signUpDTO){
        return new UserDTO(1l,signUpDTO.getFirstName(),signUpDTO.getLastName(),signUpDTO.getLogin(),"token", Collections.EMPTY_LIST);
    }
}
