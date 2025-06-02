package com.spacecodee.spring_security_music_p.data.dto;

import java.io.Serializable;

public record OperationDTO(
        int id,
        String tag,
        String path,
        String httpMethod,
        boolean permitAll,
        ModuleDTO moduleDTO) implements Serializable {
}
