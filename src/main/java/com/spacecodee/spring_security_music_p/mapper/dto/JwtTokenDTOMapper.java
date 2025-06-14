package com.spacecodee.spring_security_music_p.mapper.dto;

import com.spacecodee.spring_security_music_p.data.dto.security.UDJwtTokenDTO;
import com.spacecodee.spring_security_music_p.persisance.entity.JwtTokenEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {GenreDTOMapper.class})
public interface JwtTokenDTOMapper {

    @Mapping(target = "id", source = "jwtTokenId")
    @Mapping(target = "token", source = "jwtTokenToken")
    @Mapping(target = "valid", source = "jwtTokenIsValid")
    @Mapping(target = "expiryDate", source = "jwtTokenExpiryDate")
    @Mapping(target = "userSDTO", source = "userSEntity")
    UDJwtTokenDTO toDto(JwtTokenEntity jwtTokenEntity);

}
