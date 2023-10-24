package com.joan.security.controller;

import com.joan.security.config.UserAuthenticationProvider;
import com.joan.security.dto.SignUpDTO;
import com.joan.security.dto.UserDTO;
import com.joan.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @PostMapping("/log-in")
    public ResponseEntity<UserDTO> login(@AuthenticationPrincipal UserDTO userDTO) throws ParseException {
        userDTO.setToken(userAuthenticationProvider.createToken(userDTO));
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserDTO> register(@RequestBody SignUpDTO signUpDTO) {
        UserDTO createdUser = userService.signUp(signUpDTO);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/log-out")
    public ResponseEntity<Void> logout(@AuthenticationPrincipal UserDTO signUpDTO) {
        SecurityContextHolder.clearContext();
        return ResponseEntity.noContent().build();
    }
}
