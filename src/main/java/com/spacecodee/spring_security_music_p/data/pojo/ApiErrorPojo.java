package com.spacecodee.spring_security_music_p.data.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ApiErrorPojo implements Serializable {

    private String backendMessage;
    private String message;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate timestamp;
    private String path;
    private String method;


}
