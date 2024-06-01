package com.hades.foxtube.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
@Component
public class JwtUtils {
  private final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration-millisecond}")
  private Long expirationTime;

  public String generateToken(String username) {
    return JWT.create()
        .withSubject(username)
        .withIssuedAt(new Date())
        .withExpiresAt(new Date(new Date().getTime() + expirationTime))
        .sign(getAlgorithm());
  }

  private Algorithm getAlgorithm() {
    return Algorithm.HMAC512(secret);
  }

  public String getUsername(String token) {
    return JWT.require(getAlgorithm()).build().verify(token).getSubject();
  }

  public boolean validateToken(String token) {
    try {
      JWT.require(getAlgorithm()).build().verify(token);
      return true;
    } catch (Exception e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    }
    return false;
  }
}
