package com.marketplace.usuario.security;
import com.marketplace.usuario.model.Usuario; // Tu clase Usuario
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.expiration-ms:86400000}")
    private long expirationMs;

    private SecretKey signingKey;

    @PostConstruct
    void init() {
        // Transformamos tu secreto del application.properties en una llave real
        signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(Usuario usuario) {
        // Adaptado a los campos: getRol() y isActivo()
        return Jwts.builder()
                .claims(Map.of(
                        "role", usuario.getRol(),
                        "active", usuario.isActivo()
                ))
                .subject(usuario.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(signingKey)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, String expectedUsername) {
        // Comprobamos que el usuario coincida y que el token no haya vencido
        return expectedUsername.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public long getExpirationMs() {
        return expirationMs;
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        // Esta es la forma moderna de leer los datos del token en JJWT 0.12+
        Claims claims = Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claimsResolver.apply(claims);
    }
}
