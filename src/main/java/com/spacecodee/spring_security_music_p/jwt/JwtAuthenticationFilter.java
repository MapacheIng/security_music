package com.spacecodee.spring_security_music_p.jwt;

import com.spacecodee.spring_security_music_p.data.dto.security.UDJwtTokenDTO;
import com.spacecodee.spring_security_music_p.data.dto.security.UDUserSDTO;
import com.spacecodee.spring_security_music_p.data.vo.JwtTokenVO;
import com.spacecodee.spring_security_music_p.jwt.service.JwtService;
import com.spacecodee.spring_security_music_p.mapper.vo.JwtTokenVOMapper;
import com.spacecodee.spring_security_music_p.service.JwtTokenService;
import com.spacecodee.spring_security_music_p.service.UserSService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;

@AllArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserSService userSService;
    private final JwtTokenService jwtTokenService;
    private final JwtTokenVOMapper jwtTokenVOMapper;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String jwt = this.jwtService.extractJwtFromRequest(request);
        if (!StringUtils.hasText(jwt)) {
            filterChain.doFilter(request, response);
            return;
        }

        UDJwtTokenDTO jwtTokenDTO = this.jwtTokenService.findByToken(jwt);
        boolean isValid = this.validateToken(this.jwtTokenVOMapper.dtoToVO(jwtTokenDTO));

        if (!isValid) {
            filterChain.doFilter(request, response);
            return;
        }

        String username = this.jwtService.extractUsername(jwt);
        UDUserSDTO userD = this.userSService.findOneByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, null, userD.getAuthorities());

        authenticationToken.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);


    }

    private boolean validateToken(JwtTokenVO optionalJwtToken) {
        if (optionalJwtToken == null) {
            return false;
        }

        Date now = new Date(System.currentTimeMillis());
        boolean isValid = optionalJwtToken.isValid() && optionalJwtToken.getExpiryDate().after(now);

        if (!isValid){
            this.updateToken(optionalJwtToken);
        }

        return isValid;
    }

    private void updateToken(JwtTokenVO optionalJwtToken) {
        optionalJwtToken.setValid(false);
        this.jwtTokenService.save(optionalJwtToken);
    }
}
