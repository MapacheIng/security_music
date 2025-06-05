package com.spacecodee.spring_security_music_p.data.pojo;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ApiResponsePojo implements Serializable {

    private String message;
    @Setter(AccessLevel.PRIVATE)
    private String status;
    @Setter(AccessLevel.PRIVATE)
    private int statusCode;
    @Setter(AccessLevel.PRIVATE)
    private LocalDate localDate = LocalDate.now();

    public void setHttpStatus(@NotNull HttpStatus status) {
        this.statusCode = status.value();
        this.status = status.getReasonPhrase();
    }



}
