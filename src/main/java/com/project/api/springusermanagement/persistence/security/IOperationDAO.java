package com.project.api.springusermanagement.persistence.security;

import com.project.api.springusermanagement.entities.security.Operation;

import java.util.List;

public interface IOperationDAO {

    List<Operation> findByPublicAccess();

}
