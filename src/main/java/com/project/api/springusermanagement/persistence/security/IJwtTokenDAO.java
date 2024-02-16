package com.project.api.springusermanagement.persistence.security;

import com.project.api.springusermanagement.entities.security.JwtToken;

import java.util.Optional;

public interface IJwtTokenDAO {

    Optional<JwtToken> findByToken(String jwt);

    JwtToken createOne(JwtToken jwt);

}
