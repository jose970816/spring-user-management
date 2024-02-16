package com.project.api.springusermanagement.service;

import com.project.api.springusermanagement.entities.security.Role;

import java.util.Optional;

public interface IRoleService {

    Optional<Role> findDefaultRole();

}
