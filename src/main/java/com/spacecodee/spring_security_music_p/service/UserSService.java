package com.spacecodee.spring_security_music_p.service;

import com.spacecodee.spring_security_music_p.data.dto.security.UDUserSDTO;
import com.spacecodee.spring_security_music_p.data.vo.auth.SaveCustomerSVO;
import com.spacecodee.spring_security_music_p.data.vo.auth.SaveUserSVO;

public interface UserSService {

    UDUserSDTO findOneByUsername(String username);

    void registerOneCustomer(SaveCustomerSVO newCustomer, String lang);

    void registerOneUser(SaveUserSVO newUser, String lang);

    void updateOneUser(SaveUserSVO userVO, String lang);

}
