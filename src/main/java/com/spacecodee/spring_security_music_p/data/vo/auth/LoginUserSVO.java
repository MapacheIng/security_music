package com.spacecodee.spring_security_music_p.data.vo.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class LoginUserSVO implements Serializable {

    @Size(min = 3)
    @NotEmpty
    @NotBlank
    private String username;
    @Size(min = 6)
    @NotEmpty
    @NotBlank
    private String password;

}
