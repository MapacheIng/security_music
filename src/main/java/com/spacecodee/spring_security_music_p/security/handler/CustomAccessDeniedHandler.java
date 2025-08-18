package com.spacecodee.spring_security_music_p.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.spacecodee.spring_security_music_p.data.pojo.ApiErrorPojo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ApiErrorPojo apiErrorPojo = new ApiErrorPojo();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        this.apiErrorPojo.setBackendMessage(accessDeniedException.getLocalizedMessage());
        this.apiErrorPojo.setMessage("Access Denied, you don't have permission to access this resource, please contact the administrator.");
        this.apiErrorPojo.setTimestamp(LocalDate.now());
        this.apiErrorPojo.setPath(request.getRequestURI());
        this.apiErrorPojo.setMethod(request.getMethod());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String apiErrorAsJson = objectMapper.writeValueAsString(this.apiErrorPojo);
        response.getWriter().write(apiErrorAsJson);

    }
}
