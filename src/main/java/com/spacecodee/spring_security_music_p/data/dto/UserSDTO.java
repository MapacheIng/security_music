package com.spacecodee.spring_security_music_p.data.dto;

public record UserSDTO(
        int id,
        String username,
        String name,
        String lastname,
        String email,
        RoleDTO roleSDTO
) {
}
