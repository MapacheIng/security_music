package com.spacecodee.spring_security_music_p.mapper.vo;


import com.spacecodee.spring_security_music_p.data.vo.RoleVO;
import com.spacecodee.spring_security_music_p.data.vo.auth.SaveCustomerSVO;
import com.spacecodee.spring_security_music_p.enums.RoleEnum;
import com.spacecodee.spring_security_music_p.persisance.entity.RoleEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {PermissionVOMapper.class})
public interface RoleVOMapper {

    @Mapping(source = "id", target = "roleId")
    @Mapping(source = "name", target = "roleName")
    @Mapping(target = "permissions", ignore = true)
    RoleEntity roleVOToEntity(RoleVO roleVO);

    @Mapping(source = "roleId", target = "id")
    @Mapping(source = "roleName", target = "name")
    @Mapping(target = "permissions", ignore = true)
    RoleVO roleEntityToVO(RoleEntity roleEntity);

    @Mapping(source = "roleId", target = "roleId")
    @Named("mapIntToRoleEntity")
    RoleEntity mapIntToRoleEntity(Integer roleId);

    @Named("setDefaultRole")
    default RoleEntity setDefaultRole(RoleVO roleVO) {
        if (roleVO != null) {
            return this.roleVOToEntity(roleVO);
        }
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName(RoleEnum.CUSTOMER);
        return roleEntity;
    }

    @Named("setDefaultRole2")
    default RoleEntity setDefaultRole(SaveCustomerSVO roleVO) {
        if (roleVO != null) {
            return this.roleVOToEntity(new RoleVO());
        }
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName(RoleEnum.CUSTOMER);
        return roleEntity;
    }

    @Mapping(source = "id", target = "roleId")
    @Mapping(source = "name", target = "roleName")
    @Mapping(target = "permissions", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    RoleEntity partialUpdate(RoleVO roleVO, @MappingTarget RoleEntity roleEntity);


}
