package com.joan.security.dto;

import lombok.Data;

@Data
public class CredentialsDTO {
    private String login;
    private char[] password;
}
