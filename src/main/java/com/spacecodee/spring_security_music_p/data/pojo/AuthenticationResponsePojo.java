package com.spacecodee.spring_security_music_p.data.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class AuthenticationResponsePojo implements Serializable {

    private String jwt;

}
