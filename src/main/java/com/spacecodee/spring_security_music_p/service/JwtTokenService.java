package com.spacecodee.spring_security_music_p.service;

import com.spacecodee.spring_security_music_p.data.dto.security.UDJwtTokenDTO;
import com.spacecodee.spring_security_music_p.data.vo.JwtTokenVO;

public interface JwtTokenService {

    UDJwtTokenDTO findByToken(String token, String lang);

    UDJwtTokenDTO findByToken(String token);

    void save(JwtTokenVO jwtTokenVO);


}
