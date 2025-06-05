package com.spacecodee.spring_security_music_p.data.vo.auth;

import com.spacecodee.spring_security_music_p.data.vo.RoleVO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class SaveCustomerSVO implements Serializable {

    @Size(min = 4)
    @NotEmpty
    @NotBlank
    private String username;
    @Size(min = 6)
    @NotEmpty
    @NotBlank
    private String password;
    @Size(min = 6)
    @NotEmpty
    @NotBlank
    private String repeatedPassword;
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
    private RoleVO roleVO;

}
