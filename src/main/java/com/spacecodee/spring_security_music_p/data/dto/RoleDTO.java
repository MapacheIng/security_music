package com.spacecodee.spring_security_music_p.data.dto;



import java.io.Serializable;

public record RoleDTO(
        int id,
        String name) implements Serializable {
}
