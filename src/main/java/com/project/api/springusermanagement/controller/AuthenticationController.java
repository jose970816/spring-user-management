package com.project.api.springusermanagement.controller;

import com.project.api.springusermanagement.dto.LogoutResponseDTO;
import com.project.api.springusermanagement.dto.auth.AuthenticationRequestDTO;
import com.project.api.springusermanagement.dto.auth.AuthenticationResponseDTO;
import com.project.api.springusermanagement.entities.security.User;
import com.project.api.springusermanagement.service.auth.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/validate-token")
    public ResponseEntity<Boolean> validate(@RequestParam String jwt){
        boolean isTokenValid = authenticationService.validateToken(jwt);
        return ResponseEntity.ok(isTokenValid);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody @Valid AuthenticationRequestDTO authenticationRequest){
        AuthenticationResponseDTO rsp = authenticationService.login(authenticationRequest);
        return ResponseEntity.ok().body(rsp);
    }

    @PostMapping("/logout")
    public ResponseEntity<LogoutResponseDTO> logout(HttpServletRequest request){
        authenticationService.logout(request);
        return ResponseEntity.ok().body(new LogoutResponseDTO("Logout exitoso"));
    }

    @GetMapping("/profile")
    public ResponseEntity<User> findMyProfile(){
        User user = authenticationService.findLoggedInUser();
        return ResponseEntity.ok(user);
    }
}
