package com.spacecodee.spring_security_music_p.service;

import com.spacecodee.spring_security_music_p.data.dto.security.UDRoleDTO;
import com.spacecodee.spring_security_music_p.data.vo.RoleVO;
import com.spacecodee.spring_security_music_p.enums.RoleEnum;

public interface RoleService {

    UDRoleDTO findDefaultRole(String lang);

    RoleVO searchDefaultRole(String lang);

    RoleVO searchDefaultRole();

    RoleEnum getRoleEnum(String roleName, String lang);

    boolean existsById(int roleId);

}
