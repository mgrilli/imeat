package br.com.tirolezo.imeatapi.configs.security;

import br.com.tirolezo.imeatapi.domain.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("imeat-api")
                    .withSubject(user.getLogin())
                    .withExpiresAt(expiratonDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            //todo Criar Exception apropriada
            throw new RuntimeException("Error while generating token ", e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("imeat-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException j) {
            //todo Criar Exception apropriada
            return "";
        }
    }

    private Instant expiratonDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
