package com.spacecodee.spring_security_music_p.mapper.dto;

import com.spacecodee.spring_security_music_p.data.dto.ModuleDTO;
import com.spacecodee.spring_security_music_p.persisance.entity.ModuleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ModuleDTOMapper {

    @Mapping(target = "id", source = "moduleId")
    @Mapping(target = "name", source = "moduleName")
    @Mapping(target = "basePath", source = "moduleBasePath")
    ModuleDTO toDto(ModuleEntity moduleEntity);

}
