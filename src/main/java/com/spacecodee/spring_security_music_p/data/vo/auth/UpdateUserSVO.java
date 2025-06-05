package com.spacecodee.spring_security_music_p.data.vo.auth;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateUserSVO implements Serializable {

    @Positive
    private int id;
    @Size(min = 4)
    @NotEmpty
    @NotBlank
    private String username;
    @Size(min = 4)
    @NotEmpty
    @NotBlank
    private String name;
    @Size(min = 4)
    @NotEmpty
    @NotBlank
    private String lastname;
    @Size(min = 4)
    @Email
    @NotEmpty
    @NotBlank
    private String email;
    @Positive
    private int roleId;

}
