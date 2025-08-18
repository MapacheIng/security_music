package com.spacecodee.spring_security_music_p.security.authorization;

import com.spacecodee.spring_security_music_p.data.dto.security.UDOperationDTO;
import com.spacecodee.spring_security_music_p.data.dto.security.UDPermissionDTO;
import com.spacecodee.spring_security_music_p.data.dto.security.UDUserSDTO;
import com.spacecodee.spring_security_music_p.service.OperationService;
import com.spacecodee.spring_security_music_p.service.UserSService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
@Component
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private final OperationService operationService;
    private final UserSService userSService;

    @Override
    public void verify(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        AuthorizationManager.super.verify(authentication, object);
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        HttpServletRequest request = object.getRequest();
        String url = this.extractUrl(request);
        String httpMethod = request.getMethod();
        boolean isPublic = this.isPublic(url, httpMethod);
        if (isPublic) {
            return new AuthorizationDecision(true);
        }
        boolean isGranted = this.isGranted(authentication.get(), url, httpMethod);

        return new AuthorizationDecision(isGranted);
    }


    private boolean isGranted(Authentication auth, String url, String httpMethod) {
        if (!(auth instanceof UsernamePasswordAuthenticationToken)){
            throw new AuthenticationCredentialsNotFoundException("Authentication credentials were not found.");
        }

        List<UDOperationDTO> operations = this.obtainOperations(auth);
        return operations.stream()
                .anyMatch(CustomAuthorizationManager.getOperationDtoPredicate(url, httpMethod));
    }

    private List<UDOperationDTO> obtainOperations(Authentication auth) {
        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) auth;
        String username = authToken.getPrincipal().toString();
        UDUserSDTO user = this.userSService.findOneByUsername(username);
        return user.getRoleDTO().getPermissionsDTO()
                .stream()
                .map(UDPermissionDTO::getOperationDTO)
                .toList();
    }

    private boolean isPublic(String url, String httpMethod) {
        List<UDOperationDTO> publicAccessEndpoints = this.operationService.findByPublicAccess();
        return  publicAccessEndpoints.stream().anyMatch(CustomAuthorizationManager.getOperationDtoPredicate(url, httpMethod));
    }

    private static Predicate<UDOperationDTO> getOperationDtoPredicate(String url, String httpMethod) {
        return operation -> {
            String basePath = operation.getModuleDTO().getBasePath();
            Pattern pattern = Pattern.compile(basePath.concat(operation.getPath()));
            Matcher matcher = pattern.matcher(url);

            return matcher.matches() && operation.getHttpMethod().equals(httpMethod);
        };
    }

    private String extractUrl(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String url = request.getRequestURI();
        url = url.replace(contextPath, "");
        return url;
    }

}
