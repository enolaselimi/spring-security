package com.joan.security.config;

public enum Paths {
    LOGIN("/v1/auth/log-in"), SIGN_UP("/v1/auth/sign-up");

    private String value;

    Paths(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
