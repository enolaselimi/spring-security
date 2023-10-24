package com.joan.security.dto;

import lombok.Data;

@Data
public class SignUpDTO {
    private String firstName;
    private String lastName;
    private String login;
    private char[] password;
}
