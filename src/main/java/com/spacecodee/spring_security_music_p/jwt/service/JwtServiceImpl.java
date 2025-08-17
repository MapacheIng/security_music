package com.spacecodee.spring_security_music_p.jwt.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;


@Service
public class JwtServiceImpl implements JwtService {

    @Value("${security.jwt.expiration-in-minutes}")
    private long expirationInMinutes;
    @Value("${security.jwt.secret-key}")
    private String secretKey;


    @Override
    public String generateToken(UserDetails user, Map<String, Object> extraClaims) {
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date((this.expirationInMinutes * 60 * 1000) + issuedAt.getTime());
        return Jwts.builder()
                .header()
                .type("JWT")
                .and()
                .subject(user.getUsername())
                .issuedAt(issuedAt)
                .expiration(expiration)
                .claims(extraClaims)
                .signWith(this.generateKey(), Jwts.SIG.HS256)
                .compact();
    }



    @Override
    public Claims extractUsername(String jwt) {
        return this.extractAllClaims(jwt);
    }



    @Override
    public String extractJwtFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null ||
                !authorizationHeader.startsWith("Bearer ") ||
                !StringUtils.hasText(authorizationHeader)) {
            return null;
        }
        return authorizationHeader.split(" ")[1];
    }

    @Override
    public Date extractExpiration(String jwt) {
        return this.extractAllClaims(jwt).getExpiration();
    }

    private SecretKey generateKey() {
        byte[] passDecoder = Decoders.BASE64.decode(this.secretKey);
        return Keys.hmacShaKeyFor(passDecoder);
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parser()
                .verifyWith(this.generateKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }
}
