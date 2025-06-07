package com.spacecodee.spring_security_music_p.mapper.vo;


import com.spacecodee.spring_security_music_p.data.vo.ModuleVO;
import com.spacecodee.spring_security_music_p.persisance.entity.ModuleEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ModuleVOMapper {

    @Mapping(source = "basePath", target = "moduleBasePath")
    @Mapping(source = "name", target = "moduleName")
    @Mapping(source = "id", target = "moduleId")
    ModuleEntity toEntity(ModuleVO moduleVO);

    @InheritInverseConfiguration(name = "toEntity")
    ModuleVO toDto(ModuleEntity moduleEntity);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ModuleEntity partialUpdate(ModuleVO moduleVO, @MappingTarget ModuleEntity moduleEntity);
}
