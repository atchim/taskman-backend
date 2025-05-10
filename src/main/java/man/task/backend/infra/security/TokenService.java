package man.task.backend.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.*;

import man.task.backend.domain.user.User;

@Service
public class TokenService {
  @Value("${api.security.token.secret}")
  private String secret;

  static String issuer = "login-auth-api";

  public String generateToken(User user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);

      String token = JWT
        .create()
        .withIssuer(issuer)
        .withSubject(user.getEmail())
        .withExpiresAt(generateExpiratioDate())
        .sign(algorithm);

      return token;
    } catch (JWTCreationException exception) {
      throw new RuntimeException("Error while authenticating");
    }
  }

  public Optional<String> validateToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);

      String email = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()
        .verify(token)
        .getSubject();

      return Optional.of(email);
    } catch (JWTVerificationException exception) {
      return Optional.empty();
    }
  }

  private Instant generateExpiratioDate() {
    return LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.of("-3"));
  }
}
