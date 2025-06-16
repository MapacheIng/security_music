package com.spacecodee.spring_security_music_p.service.impl;


import com.spacecodee.spring_security_music_p.data.dto.security.UDUserSDTO;
import com.spacecodee.spring_security_music_p.exceptions.ObjectNotFoundException;
import com.spacecodee.spring_security_music_p.mapper.dto.UserSDTOMapper;
import com.spacecodee.spring_security_music_p.persisance.entity.UserSEntity;
import com.spacecodee.spring_security_music_p.persisance.repository.UserSRepository;
import com.spacecodee.spring_security_music_p.service.UDUserSService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UDUserSServiceImpl implements UDUserSService {

    private final UserSRepository userSRepository;
    private final UserSDTOMapper userSDTOMapper;

    public UDUserSServiceImpl(UserSRepository userSRepository, UserSDTOMapper userSDTOMapper) {
        this.userSRepository = userSRepository;
        this.userSDTOMapper = userSDTOMapper;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public UDUserSDTO findByUsername(String username) {
        Optional<UserSEntity> user = this.userSRepository.findByUserSUsername(username);

        return user.map(this.userSDTOMapper::entityToDto)
                .orElseThrow(() -> new ObjectNotFoundException("User not found with username: " + username));
    }
}
