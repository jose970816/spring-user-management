package com.project.api.springusermanagement.persistence.impl.security;

import com.project.api.springusermanagement.entities.security.JwtToken;
import com.project.api.springusermanagement.persistence.security.IJwtTokenDAO;
import com.project.api.springusermanagement.repository.security.JwtTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JwtTokenDAOImpl implements IJwtTokenDAO {

    @Autowired
    private JwtTokenRepository jwtTokenRepository;

    @Override
    public Optional<JwtToken> findByToken(String jwt) {
        return jwtTokenRepository.findByToken(jwt);
    }

    @Override
    public JwtToken createOne(JwtToken jwt) {
        return jwtTokenRepository.save(jwt);
    }

}
