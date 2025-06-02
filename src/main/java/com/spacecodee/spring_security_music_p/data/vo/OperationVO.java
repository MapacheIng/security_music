package com.spacecodee.spring_security_music_p.data.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class OperationVO implements Serializable {
    private int id;
    private String tag;
    private String path;
    private String httpMethod;
    private boolean permitAll;
    private ModuleVO moduleVO;
}
