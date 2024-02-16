package com.project.api.springusermanagement.service.security.impl;

import com.project.api.springusermanagement.entities.security.JwtToken;
import com.project.api.springusermanagement.persistence.security.IJwtTokenDAO;
import com.project.api.springusermanagement.service.security.IJwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtTokenServiceImpl implements IJwtTokenService {

    @Autowired
    private IJwtTokenDAO jwtTokenDAO;

    @Override
    public Optional<JwtToken> findByToken(String jwt) {
        return jwtTokenDAO.findByToken(jwt);
    }

    @Override
    public JwtToken createOne(JwtToken jwtToken) {
        return null;
    }

}
