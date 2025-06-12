package com.spacecodee.spring_security_music_p.service.impl;

import com.spacecodee.spring_security_music_p.data.dto.security.UDJwtTokenDTO;
import com.spacecodee.spring_security_music_p.data.vo.JwtTokenVO;
import com.spacecodee.spring_security_music_p.exceptions.ExceptionShortComponent;
import com.spacecodee.spring_security_music_p.mapper.dto.JwtTokenDTOMapper;
import com.spacecodee.spring_security_music_p.mapper.vo.JwtTokenVOMapper;
import com.spacecodee.spring_security_music_p.persisance.entity.JwtTokenEntity;
import com.spacecodee.spring_security_music_p.persisance.repository.JwtTokenRepository;
import com.spacecodee.spring_security_music_p.service.JwtTokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    private final JwtTokenRepository jwtTokenRepository;
    private final JwtTokenVOMapper jwtTokenVOMapper;
    private final JwtTokenDTOMapper jwtTokenDTOMapper;
    private final ExceptionShortComponent exceptionShortComponent;

    public JwtTokenServiceImpl(JwtTokenRepository jwtTokenRepository, JwtTokenVOMapper jwtTokenVOMapper,
                                JwtTokenDTOMapper jwtTokenDTOMapper, ExceptionShortComponent exceptionShortComponent) {
        this.jwtTokenRepository = jwtTokenRepository;
        this.jwtTokenVOMapper = jwtTokenVOMapper;
        this.jwtTokenDTOMapper = jwtTokenDTOMapper;
        this.exceptionShortComponent = exceptionShortComponent;
    }

    @Transactional(readOnly = true)
    @Override
    public UDJwtTokenDTO findByToken(String jwt, String lang) {
        Optional<JwtTokenEntity> token = this.jwtTokenRepository.findByJwtTokenToken(jwt);
        return token
                .map(this.jwtTokenDTOMapper::toDto)
                .orElseThrow(() ->this.exceptionShortComponent.notFoundException("token.found.not", lang));
    }

    @Transactional(readOnly = true)
    @Override
    public UDJwtTokenDTO findByToken(String jwt) {
        Optional<JwtTokenEntity> token = this.jwtTokenRepository.findByJwtTokenToken(jwt);
        return token
                .map(this.jwtTokenDTOMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Token not found"));
    }

    @Transactional
    @Override
    public void save(JwtTokenVO jwtTokenVO) {
        this.jwtTokenRepository.save(this.jwtTokenVOMapper.toEntity(jwtTokenVO));
    }
}
