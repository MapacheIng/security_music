package com.spacecodee.spring_security_music_p.data.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthenticationResponsePojo implements Serializable {

    private String jwt;

}
