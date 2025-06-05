package com.spacecodee.spring_security_music_p.data.dto.security;

import lombok.Data;

import java.io.Serializable;

@Data
public class UDModuleDTO implements Serializable {
    private int id;
    private String name;
    private String basePath;
}
