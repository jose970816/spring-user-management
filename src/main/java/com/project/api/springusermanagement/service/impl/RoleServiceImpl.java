package com.project.api.springusermanagement.service.impl;

import com.project.api.springusermanagement.entities.security.Role;
import com.project.api.springusermanagement.persistence.security.IRoleDAO;
import com.project.api.springusermanagement.repository.security.RoleRepository;
import com.project.api.springusermanagement.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDAO roleDAO;

    @Override
    public Optional<Role> findDefaultRole() {
        return roleDAO.findDefaultRole();
    }

}
