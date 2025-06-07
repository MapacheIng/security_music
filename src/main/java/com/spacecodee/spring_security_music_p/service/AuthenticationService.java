package com.spacecodee.spring_security_music_p.service;

import com.spacecodee.spring_security_music_p.data.dto.security.UDUserSDTO;
import com.spacecodee.spring_security_music_p.data.pojo.AuthenticationResponsePojo;
import com.spacecodee.spring_security_music_p.data.vo.auth.LoginUserSVO;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthenticationService {

    AuthenticationResponsePojo login(LoginUserSVO loginUserSVO);

    boolean validateToken(String jwt);

    UDUserSDTO findLoggedUser();

    void logout(HttpServletRequest request);

}
