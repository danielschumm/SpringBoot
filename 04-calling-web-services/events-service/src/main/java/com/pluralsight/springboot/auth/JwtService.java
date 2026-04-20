package com.pluralsight.springboot.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

@Service
public class JwtService {

    @Value("${JWT.secretkey}")
    private String SECRET_KEY;

    private static final long EXPIRATION_TIME = 1000 * 60 * 15; // 15 min

    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims getBodyFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            Claims claims = getBodyFromToken(token);
            String username = claims.getSubject();
            boolean expired = claims.getExpiration().before(new Date());
            return username.equals(userDetails.getUsername()) && !expired;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(Claims claims) {
        return claims.getSubject();
    }
    public boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }
}
