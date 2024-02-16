package com.project.api.springusermanagement.config.security.authorization;

import com.project.api.springusermanagement.exception.ObjectNotFoundException;
import com.project.api.springusermanagement.entities.security.GrantedPermission;
import com.project.api.springusermanagement.entities.security.Operation;
import com.project.api.springusermanagement.entities.security.User;
import com.project.api.springusermanagement.repository.security.OperationRepository;
import com.project.api.springusermanagement.service.IUserService;
import com.project.api.springusermanagement.service.security.IOperationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.stream.Collectors;

@Component
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    @Autowired
    private IOperationService operationService;

    @Autowired
    private IUserService userService;

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext requestContext) {
        HttpServletRequest request = requestContext.getRequest();
        String url = extractUrl(request);
        String httpMethod = request.getMethod();

        boolean isPublic = isPublic(url, httpMethod);

        if(isPublic)
            return new AuthorizationDecision(true);

        boolean isGranted = isGranted(url,httpMethod, authentication.get());

        return new AuthorizationDecision(isGranted);
    }

    private boolean isGranted(String url, String httpMethod, Authentication authentication) {
        if(!(authentication instanceof UsernamePasswordAuthenticationToken))
            throw new AuthenticationCredentialsNotFoundException("User not logged in");

        List<Operation> operations = obtainedOperations(authentication);

        return operations.stream().anyMatch(getOperationPredicate(url, httpMethod));
    }

    private List<Operation> obtainedOperations(Authentication authentication) {
        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
        String username = (String) authToken.getPrincipal();
        User user = userService.findByUsername(username).orElseThrow(() -> new ObjectNotFoundException("User not found. Username: " + username));

        return user.getRole().getPermissions().stream()
                .map(GrantedPermission::getOperation)
                .collect(Collectors.toList());

    }

    private static Predicate<Operation> getOperationPredicate(String url, String httpMethod) {
        return operation -> {
            String basePath = operation.getModule().getBasePath();
            Pattern pattern = Pattern.compile(basePath.concat(operation.getPath()));
            Matcher matcher = pattern.matcher(url);

            return matcher.matches() && operation.getHttpMethod().equals(httpMethod);
        };
    }

    private boolean isPublic(String url, String httpMethod) {
        List<Operation> publicAccessEndpoint = operationService.findByPublicAccess();
        publicAccessEndpoint.forEach(System.out::println);

        return publicAccessEndpoint.stream().anyMatch(getOperationPredicate(url, httpMethod));
    }

    private String extractUrl(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String url = request.getRequestURI();
        url = url.replace(contextPath, "");

        return url;
    }

}
