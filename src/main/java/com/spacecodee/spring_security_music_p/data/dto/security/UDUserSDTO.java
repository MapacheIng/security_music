package com.spacecodee.spring_security_music_p.data.dto.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

// esta clases de UD son para implementarlas con las de UserDetails

@Data
public class UDUserSDTO implements Serializable {

    private int id;
    private String name;
    private String username;
    @JsonIgnore
    private String password;
    private UDRoleDTO roleEntity;

}
