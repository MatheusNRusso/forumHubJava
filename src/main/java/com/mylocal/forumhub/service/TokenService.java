package com.mylocal.forumhub.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.mylocal.forumhub.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Usuario usuario) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("API REST ForumHub")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(dateExpirate())
                    .sign(algorithm);
            return token;
        }
        catch (JWTCreationException exception) {
            throw new RuntimeException("Invalid Signing configuration / Couldn't convert Claims.", exception);
        }
    }

    public String getSubject(String token) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API REST ForumHub")
                    .build()
                    .verify(token)
                    .getSubject();

        }
        catch (JWTCreationException exception) {
            throw new RuntimeException("Invalid Signing configuration / Couldn't convert Claims.", exception);
        }
    }

    private Instant dateExpirate() {
        return Instant.now().plusSeconds(3600);

    }
}