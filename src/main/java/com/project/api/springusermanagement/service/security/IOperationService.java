package com.project.api.springusermanagement.service.security;

import com.project.api.springusermanagement.entities.security.Operation;

import java.util.List;

public interface IOperationService {

    List<Operation> findByPublicAccess();

}
