package com.joan.security.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.joan.security.dto.CredentialsDTO;
import com.joan.security.dto.UserDTO;
import com.joan.security.service.AuthenticationService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserAuthenticationProvider {

    private final AuthenticationService authenticationService;
    @Value(
            "${security.jwt.token.secret-key}"
    )
    private String secretKey;

    public UserAuthenticationProvider(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String login) throws ParseException {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000);

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withIssuer(login)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(algorithm);
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        UserDTO userDTO = authenticationService.findByLogin(decodedJWT.getIssuer());
        return new UsernamePasswordAuthenticationToken(userDTO, null, userDTO.getAuthorities());
    }

    public Authentication validateCredentials(CredentialsDTO credentialsDTO) {
        UserDTO userDTO = authenticationService.authenticate(credentialsDTO);
        return new UsernamePasswordAuthenticationToken(userDTO, null, Collections.EMPTY_LIST);
    }
}
