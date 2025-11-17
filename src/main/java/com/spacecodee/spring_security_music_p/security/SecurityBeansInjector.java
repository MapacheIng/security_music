package com.spacecodee.spring_security_music_p.security;

import com.spacecodee.spring_security_music_p.service.UDUserSService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityBeansInjector {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final UDUserSService udUserSService;

    public SecurityBeansInjector(AuthenticationConfiguration authenticationConfiguration, UDUserSService udUserSService) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.udUserSService = udUserSService;
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return this.authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userDetailsService());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return this.udUserSService::findByUsername;
    }


}
