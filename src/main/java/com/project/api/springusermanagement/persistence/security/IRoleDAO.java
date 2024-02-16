package com.project.api.springusermanagement.persistence.security;

import com.project.api.springusermanagement.entities.security.Role;

import java.util.Optional;

public interface IRoleDAO {

    Optional<Role> findDefaultRole();

}
