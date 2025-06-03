package com.spacecodee.spring_security_music_p.mapper.dto;

import com.spacecodee.spring_security_music_p.data.dto.PermissionDTO;
import com.spacecodee.spring_security_music_p.persisance.entity.PermissionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {OperationDTOMapper.class})
public interface PermissionDTOMapper {

    @Mapping(target = "id", source = "permissionId")
    @Mapping(target = "roleDTO", ignore = true)
    @Mapping(target = "operationDTO", source = "operationEntity")
    PermissionDTO toDto(PermissionEntity permissionEntity);

}
