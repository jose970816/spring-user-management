package com.project.api.springusermanagement.repository.security;

import com.project.api.springusermanagement.entities.security.GrantedPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<GrantedPermission, Long> {
}
