package com.spacecodee.spring_security_music_p.data.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PermissionVO implements Serializable {
    private int id;
    private RoleVO roleVO;
    private OperationVO operationVO;
}
