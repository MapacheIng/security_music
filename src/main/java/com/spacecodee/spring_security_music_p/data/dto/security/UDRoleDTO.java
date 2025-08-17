package com.spacecodee.spring_security_music_p.data.dto.security;

import com.spacecodee.spring_security_music_p.enums.RoleEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
public class UDRoleDTO implements Serializable {

    private int id;
    private RoleEnum name;
    private List<UDPermissionDTO> permissionsDTO;

}
