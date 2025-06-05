package com.spacecodee.spring_security_music_p.data.dto.security;

import lombok.Data;

import java.io.Serializable;

@Data
public class UDOperationDTO implements Serializable {
    private int id;
    private String tag;
    private String path;
    private String httpMethod;
    private boolean permitAll;
    private UDModuleDTO moduleDTO;
}
