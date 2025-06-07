package com.spacecodee.spring_security_music_p.data.vo;

import com.spacecodee.spring_security_music_p.enums.RoleEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class RoleVO implements Serializable {
    private int id;
    private RoleEnum name;
    private Set<PermissionVO> permissions;
}
