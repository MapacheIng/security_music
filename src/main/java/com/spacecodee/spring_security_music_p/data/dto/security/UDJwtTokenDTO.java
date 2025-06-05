package com.spacecodee.spring_security_music_p.data.dto.security;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UDJwtTokenDTO implements Serializable {

    private int id;
    private String token;
    private boolean valid;
    private Date expiryDate;

}
