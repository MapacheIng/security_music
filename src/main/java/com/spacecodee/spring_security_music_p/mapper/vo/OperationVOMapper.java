package com.spacecodee.spring_security_music_p.mapper.vo;

import com.spacecodee.spring_security_music_p.data.vo.OperationVO;
import com.spacecodee.spring_security_music_p.persisance.entity.OperationEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OperationVOMapper {

    @Mapping(source = "permitAll", target = "operationPermitAll")
    @Mapping(source = "httpMethod", target = "operationHttpMethod")
    @Mapping(source = "path", target = "operationPath")
    @Mapping(source = "tag", target = "operationTag")
    @Mapping(source = "id", target = "operationId")
    OperationEntity toEntity(OperationVO operationVO);

    @InheritInverseConfiguration(name = "toEntity")
    OperationVO toDto(OperationEntity operationEntity);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OperationEntity partialUpdate(OperationVO operationVO, @MappingTarget OperationEntity operationEntity);

}
