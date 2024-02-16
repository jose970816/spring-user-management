package com.project.api.springusermanagement.persistence.impl.security;

import com.project.api.springusermanagement.entities.security.Role;
import com.project.api.springusermanagement.persistence.security.IRoleDAO;
import com.project.api.springusermanagement.repository.security.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleDAOImpl implements IRoleDAO {

    @Value("${security.default.role}")
    private String defaultRole;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Role> findDefaultRole() {
        return roleRepository.findByName(defaultRole);
    }

}
