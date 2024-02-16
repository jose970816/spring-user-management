package com.project.api.springusermanagement.service.security;

import com.project.api.springusermanagement.entities.security.JwtToken;

import java.util.Optional;

public interface IJwtTokenService {

    Optional<JwtToken> findByToken(String jwt);

    JwtToken createOne(JwtToken jwtToken);

}
