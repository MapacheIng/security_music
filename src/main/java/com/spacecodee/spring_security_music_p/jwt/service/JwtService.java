package com.spacecodee.spring_security_music_p.jwt.service;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Map;

public interface JwtService {

    String generateToken(UserDetails user, Map<String, Object> extraClaims);
    String extractUsername(String jwt);
    String extractJwtFromRequest(HttpServletRequest request);
    Date extractExpiration(String jwt);

}
