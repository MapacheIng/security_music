package com.spacecodee.spring_security_music_p.service;

import com.spacecodee.spring_security_music_p.data.dto.security.UDUserSDTO;

public interface UDUserSService {

    UDUserSDTO findByUsername(String username);

}
