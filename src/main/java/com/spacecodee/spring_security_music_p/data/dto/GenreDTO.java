package com.spacecodee.spring_security_music_p.data.dto;

import java.io.Serializable;

public record GenreDTO(
        int id,
        String name,
        String enabled) implements Serializable {
}
