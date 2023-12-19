package br.com.api.parking.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

  @Value("${api.security.token.secret}")
  private String JWT_SECRET_KEY;

  public String generateToken(User user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_KEY);
      return JWT.create().
              withIssuer("auth0-api")
              .withSubject(user.getUsername())
              .withExpiresAt(Instant.MAX)
              .sign(algorithm);
    } catch (JWTCreationException ex) {
      throw new JWTCreationException("Erro na criacao do Token!", ex);
    }
  }

  public String validateToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_KEY);
      return JWT.require(algorithm)
              .withIssuer("auth0-api")
              .build()
              .verify(token)
              .getSubject();
    } catch (JWTVerificationException ex) {
      throw new JWTVerificationException("Token inválido ou expirado!", ex);
    }
  }
}
