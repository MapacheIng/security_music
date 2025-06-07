package com.spacecodee.spring_security_music_p.mapper.vo;


import com.spacecodee.spring_security_music_p.data.vo.auth.LoginUserSVO;
import com.spacecodee.spring_security_music_p.data.vo.auth.SaveCustomerSVO;
import com.spacecodee.spring_security_music_p.data.vo.auth.SaveUserSVO;
import com.spacecodee.spring_security_music_p.persisance.entity.UserSEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {RoleVOMapper.class})
public interface UserSVOMapper {

    @Mapping(source = "username", target = "userSUsername")
    @Mapping(source = "password", target = "userSPassword")
    UserSEntity toEntity(LoginUserSVO loginUserSVO);


    @Mapping(source = "roleVO", target = "roleEntity", qualifiedByName = "setDefaultRole")
    @Mapping(source = "email", target = "userSEmail")
    @Mapping(source = "lastname", target = "userSLastname")
    @Mapping(source = "name", target = "userSName")
    @Mapping(source = "password", target = "userSPassword")
    @Mapping(source = "username", target = "userSUsername")
    UserSEntity toEntity(SaveCustomerSVO saveCustomerSVO);

    @Mapping(target = "idUserS", source = "id")
    @Mapping(target = "roleEntity", source = "roleId", qualifiedByName = "setDefaultRole")
    @Mapping(target = "userSEmail", source = "email")
    @Mapping(target = "userSLastname", source = "lastname")
    @Mapping(target = "userSName", source = "name")
    @Mapping(target = "userSUsername", source = "username")
    UserSEntity voToEntity(SaveUserSVO userVO);

    @Mapping(target = "idUserS", source = "id")
    @Named("mapIntToUserSEntity")
    UserSEntity mapIntToUserSEntity(Integer id);

}
