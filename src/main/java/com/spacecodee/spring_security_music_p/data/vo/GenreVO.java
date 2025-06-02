package com.spacecodee.spring_security_music_p.data.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class GenreVO implements Serializable {
    private int id;
    private String name;
    private String enabled;
}
