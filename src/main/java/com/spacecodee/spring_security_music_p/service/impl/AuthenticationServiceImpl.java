package com.spacecodee.spring_security_music_p.service.impl;

import com.spacecodee.spring_security_music_p.data.dto.security.UDUserSDTO;
import com.spacecodee.spring_security_music_p.data.pojo.AuthenticationResponsePojo;
import com.spacecodee.spring_security_music_p.data.vo.auth.LoginUserSVO;
import com.spacecodee.spring_security_music_p.mapper.vo.JwtTokenVOMapper;
import com.spacecodee.spring_security_music_p.service.AuthenticationService;
import com.spacecodee.spring_security_music_p.service.JwtTokenService;
import com.spacecodee.spring_security_music_p.service.UserSService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserSService userSService;
//    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final JwtTokenVOMapper jwtTokenVOMapper;

    public AuthenticationServiceImpl(UserSService userSService, AuthenticationManager authenticationManager,
                                        JwtTokenService jwtTokenService, JwtTokenVOMapper jwtTokenVOMapper) {
        this.userSService = userSService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.jwtTokenVOMapper = jwtTokenVOMapper;
    }

    @Override
    public AuthenticationResponsePojo login(LoginUserSVO loginUserSVO) {
        return null;
    }

    @Override
    public boolean validateToken(String jwt) {
        return false;
    }

    @Override
    public UDUserSDTO findLoggedUser() {
        return null;
    }

    @Override
    public void logout(HttpServletRequest request) {

    }

    private Map<String, Object> generateExtraClaims(UDUserSDTO userD){
        return Map.of(
                "name", userD.getName(),
                "role", userD.getRoleDTO(),
                "authorities", userD.getAuthorities()
        );
    }

}
