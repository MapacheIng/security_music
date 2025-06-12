package com.spacecodee.spring_security_music_p.mapper.dto;

import com.spacecodee.spring_security_music_p.data.dto.OperationDTO;
import com.spacecodee.spring_security_music_p.data.dto.security.UDOperationDTO;
import com.spacecodee.spring_security_music_p.persisance.entity.OperationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {ModuleDTOMapper.class})
public interface OperationDTOMapper {

    @Mapping(target = "id", source = "operationId")
    @Mapping(target = "tag", source = "operationTag")
    @Mapping(target = "httpMethod", source = "operationHttpMethod")
    @Mapping(target = "path", source = "operationPath")
    @Mapping(target = "permitAll", source = "operationPermitAll")
    @Mapping(target = "moduleDTO", source = "moduleEntity")
    UDOperationDTO toDto(OperationEntity operationEntity);

}
