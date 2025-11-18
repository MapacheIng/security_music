package com.spacecodee.spring_security_music_p.service.impl;

import com.spacecodee.spring_security_music_p.data.dto.security.UDJwtTokenDTO;
import com.spacecodee.spring_security_music_p.data.dto.security.UDUserSDTO;
import com.spacecodee.spring_security_music_p.data.pojo.AuthenticationResponsePojo;
import com.spacecodee.spring_security_music_p.data.vo.auth.LoginUserSVO;
import com.spacecodee.spring_security_music_p.jwt.service.JwtService;
import com.spacecodee.spring_security_music_p.mapper.vo.JwtTokenVOMapper;
import com.spacecodee.spring_security_music_p.service.AuthenticationService;
import com.spacecodee.spring_security_music_p.service.JwtTokenService;
import com.spacecodee.spring_security_music_p.service.UserSService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserSService userSService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final JwtTokenVOMapper jwtTokenVOMapper;

    public AuthenticationServiceImpl(UserSService userSService, JwtService jwtService, AuthenticationManager authenticationManager,
                                     JwtTokenService jwtTokenService, JwtTokenVOMapper jwtTokenVOMapper) {
        this.userSService = userSService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.jwtTokenVOMapper = jwtTokenVOMapper;
    }

    @Override
    public AuthenticationResponsePojo login(LoginUserSVO loginUserSVO) {
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(loginUserSVO.getUsername(), loginUserSVO.getPassword());
        this.authenticationManager.authenticate(authentication);

        UDUserSDTO username = this.userSService.findOneByUsername(loginUserSVO.getUsername());
        String jwt = this.jwtService.generateToken(username, this.generateExtraClaims(username));
        this.saveUserToken(jwt, username);
        return new AuthenticationResponsePojo(jwt);
    }

    private void saveUserToken(String jwt, UDUserSDTO username) {
        Date expiration = this.jwtService.extractExpiration(jwt);
        this.jwtTokenService.save(this.jwtTokenVOMapper.toVOFromUdVO(jwt, expiration, (int) username.getId()));
    }

    @Override
    public boolean validateToken(String jwt) {
        try {
            this.jwtService.extractUsername(jwt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public UDUserSDTO findLoggedUser() {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getPrincipal().toString();
        return this.userSService.findOneByUsername(username);
    }

    @Override
    public void logout(HttpServletRequest request) {
        String jwt = this.jwtService.extractJwtFromRequest(request);
        if (!StringUtils.hasText(jwt)){
            return;
        }
        UDJwtTokenDTO token = this.jwtTokenService.findByToken(jwt);
        if (token.isValid() && StringUtils.hasText(token.getToken())) {
            token.setValid(false);
            this.jwtTokenService.save(this.jwtTokenVOMapper.dtoToVO(token));
        }

    }

    private Map<String, Object> generateExtraClaims(UDUserSDTO userD){
        return Map.of(
                "name", userD.getName(),
                "role", userD.getRoleDTO(),
                "authorities", userD.getAuthorities()
        );
    }

}
