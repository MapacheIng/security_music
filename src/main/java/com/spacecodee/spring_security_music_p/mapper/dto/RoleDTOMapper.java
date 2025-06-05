package com.spacecodee.spring_security_music_p.mapper.dto;

import com.spacecodee.spring_security_music_p.data.dto.RoleDTO;
import com.spacecodee.spring_security_music_p.data.dto.security.UDRoleDTO;
import com.spacecodee.spring_security_music_p.persisance.entity.RoleEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {PermissionDTOMapper.class})
public interface RoleDTOMapper {
    // UD
    @Mapping(target = "id", source = "roleId")
    @Mapping(target = "name", source = "roleName")
    @Mapping(target = "permissionsDTO", source = "permissions")
    UDRoleDTO entityToUdDto(RoleEntity roleEntity);

    @Mapping(target = "id", source = "roleId")
    @Mapping(target = "name", source = "roleName")
    RoleDTO entityToDto(RoleEntity roleEntity);
}
