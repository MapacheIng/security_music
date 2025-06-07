package com.spacecodee.spring_security_music_p.mapper.vo;

import com.spacecodee.spring_security_music_p.data.dto.security.UDJwtTokenDTO;
import com.spacecodee.spring_security_music_p.data.vo.JwtTokenVO;
import com.spacecodee.spring_security_music_p.persisance.entity.JwtTokenEntity;
import org.mapstruct.*;

import java.util.Date;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {UserSVOMapper.class})
public interface JwtTokenVOMapper {

    @Mapping(target = "userSEntity", source = "userVOId", qualifiedByName = "mapIntToUserSEntity")
    @Mapping(target = "jwtTokenIsValid", source = "valid")
    @Mapping(target = "jwtTokenExpiryDate", source = "expiryDate")
    @Mapping(target = "jwtTokenToken", source = "token")
    @Mapping(target = "jwtTokenId", source = "id")
    JwtTokenEntity voToEntity(JwtTokenVO jwtTokenVO);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "token", source= "token")
    @Mapping(target = "valid", source = "valid")
    @Mapping(target = "expiryDate", source = "expiryDate")
    @Mapping(target = "UserVOId", source = "userSDTO.id")
    JwtTokenVO dtoToVO(UDJwtTokenDTO udJwtTokenDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "token", source="token")
    @Mapping(target = "valid", constant = "true")
    @Mapping(target = "UserVOId", source = "udUserSId")
    @Mapping(target = "expiryDate", source = "expiryDate")
    JwtTokenVO toVOFromUdVO(String token, Date expiryDate, int udUserSId);

    @InheritConfiguration(name = "voToEntity")
    JwtTokenEntity toEntity(JwtTokenVO jwtTokenVO);

    @Mapping(target = "userVOId", source = "userSEntity.idUserS")
    @Mapping(target = "valid", source = "jwtTokenIsValid")
    @Mapping(target = "expiryDate", source = "jwtTokenExpiryDate")
    @Mapping(target = "token", source = "jwtTokenToken")
    JwtTokenVO toVO(JwtTokenEntity jwtTokenEntity);

    @InheritConfiguration(name = "voToEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    JwtTokenEntity partialUpdate(JwtTokenVO jwtTokenVO, @MappingTarget JwtTokenEntity jwtTokenEntity);
}
