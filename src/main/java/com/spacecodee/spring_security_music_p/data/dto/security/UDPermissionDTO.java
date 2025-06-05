package com.spacecodee.spring_security_music_p.data.dto.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class UDPermissionDTO implements Serializable {

    private int id;
    @JsonIgnore
    private UDRoleDTO roleDTO;
    private UDOperationDTO operationDTO;

}
