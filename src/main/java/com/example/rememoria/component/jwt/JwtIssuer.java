package com.example.rememoria.component.jwt;

import com.sun.istack.NotNull;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

@RequiredArgsConstructor
@Component
public class JwtIssuer {
    public String issueToken(@NotNull Claims claims, @NotNull byte[] secretKeyBytes) {
        if (claims == null) {
            throw new NullPointerException("Claims cannot be null");
        }
        if (secretKeyBytes == null) {
            throw new IllegalArgumentException("Secret key cannot be null");
        }
        Key secretKey = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS256.getJcaName());
        return Jwts.builder()
                .setClaims(claims)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }
}