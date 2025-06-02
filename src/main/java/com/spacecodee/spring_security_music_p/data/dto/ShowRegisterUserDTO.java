package com.spacecodee.spring_security_music_p.data.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShowRegisterUserDTO implements Serializable {
    private int id;
    private String username;
    private String name;
    private String lastname;
    private String roleEntityRole;
    private String jwt;
}
