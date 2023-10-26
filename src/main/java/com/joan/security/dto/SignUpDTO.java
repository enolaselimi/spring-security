package com.joan.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class SignUpDTO {
    private String firstName;
    private String lastName;
    private String login;
    private char[] password;

}
