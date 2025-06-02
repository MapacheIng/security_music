package com.spacecodee.spring_security_music_p.data.dto;

import java.io.Serializable;

public record PermissionDTO(
        int id,
        RoleDTO roleDTO,
        OperationDTO operationDTO) implements Serializable {
}
