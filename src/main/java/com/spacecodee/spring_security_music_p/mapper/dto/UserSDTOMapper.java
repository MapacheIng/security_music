package com.spacecodee.spring_security_music_p.mapper.dto;

import com.spacecodee.spring_security_music_p.data.dto.ShowRegisterUserDTO;
import com.spacecodee.spring_security_music_p.data.dto.security.UDRoleDTO;
import com.spacecodee.spring_security_music_p.data.dto.security.UDUserSDTO;
import com.spacecodee.spring_security_music_p.persisance.entity.UserSEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {RoleDTOMapper.class})
public interface UserSDTOMapper {

    @Mapping(target = "id", source = "idUserS")
    @Mapping(target = "name", source = "userSName")
    @Mapping(target = "username", source = "userSUsername")
    @Mapping(target = "password", source = "userSPassword")
    @Mapping(target = "roleDTO", source = "roleEntity")
    UDUserSDTO entityToDto(UserSEntity userSEntity);

    @Mapping(target = "jwt", source = "jwt")
    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "lastname", ignore = true) // Assuming lastname is not in UDUserSDTO
    @Mapping(target = "roleEntityRole", source = "user.roleDTO.name")
    ShowRegisterUserDTO udDtoToShowDto(String jwt, UDUserSDTO user);

}
