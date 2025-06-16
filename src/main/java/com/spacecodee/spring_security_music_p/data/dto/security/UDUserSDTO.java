package com.spacecodee.spring_security_music_p.data.dto.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

// esta clases de UD son para implementarlas con las de UserDetails

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UDUserSDTO implements UserDetails, Serializable {

    private int id;
    private String name;
    private String username;
    @JsonIgnore
    private String password;
    private UDRoleDTO roleDTO;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.roleDTO == null) return Collections.emptyList();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(this.roleDTO.getPermissionsDTO()
                .stream()
                .map(each -> each.getOperationDTO().getTag())
                .map(SimpleGrantedAuthority::new)
                .toList()
        );
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.roleDTO.getName()));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
