package com.spacecodee.spring_security_music_p.data.dto;

import java.io.Serializable;

public record ModuleDTO(
        int id,
        String name,
        String description) implements Serializable {
}
