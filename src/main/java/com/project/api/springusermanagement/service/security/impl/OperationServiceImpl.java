package com.project.api.springusermanagement.service.security.impl;

import com.project.api.springusermanagement.entities.security.Operation;
import com.project.api.springusermanagement.persistence.security.IOperationDAO;
import com.project.api.springusermanagement.service.security.IOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationServiceImpl implements IOperationService {

    @Autowired
    private IOperationDAO operationDAO;

    @Override
    public List<Operation> findByPublicAccess() {
        return operationDAO.findByPublicAccess();
    }

}
