package com.spacecodee.spring_security_music_p.mapper.vo;

import com.spacecodee.spring_security_music_p.data.vo.PermissionVO;
import com.spacecodee.spring_security_music_p.persisance.entity.PermissionEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {RoleVOMapper.class, OperationVOMapper.class})
public interface PermissionVOMapper {

    @Mapping(source = "id", target = "permissionId")
    @Mapping(source = "roleVO", target = "roleEntity")
    @Mapping(source = "operationVO", target = "operationEntity")
    PermissionEntity toEntity(PermissionVO permissionVO);

    @Mapping(source = "permissionId", target = "id")
    @Mapping(source = "roleEntity", target = "roleVO")
    @Mapping(source = "operationEntity", target = "operationVO")
    PermissionVO toDto(PermissionEntity permissionEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "id", target = "permissionId")
    @Mapping(source = "roleVO", target = "roleEntity")
    @Mapping(source = "operationVO", target = "operationEntity")
    PermissionEntity partialUpdate(PermissionVO permissionVO, @MappingTarget PermissionEntity permissionEntity);

}
