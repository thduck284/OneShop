package Jwt;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import models.User;

public class JwtUtil {

    private static final String SECRET_KEY = "ygWmQJcQnXR4vL7aK8Dbf2MtpCNxZ3jP56VFAsTu9Ekr1LoHGYBzwhvJdUxq0y"; 
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;  

    @SuppressWarnings("deprecation")
    public static String generateToken(User user) {
        return Jwts.builder()
            .setSubject(user.getUserName())  
            .claim("userId", user.getUserId())  
            .claim("role", user.getRole())  
            .setIssuedAt(new Date())  
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))  
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  
            .compact();
    }

    @SuppressWarnings("deprecation")
    public static Claims validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)  
                .getBody();
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException("Invalid or expired JWT token", e);
        }
    }
}
