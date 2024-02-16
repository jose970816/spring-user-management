package com.project.api.springusermanagement.persistence.impl.security;

import com.project.api.springusermanagement.entities.security.Operation;
import com.project.api.springusermanagement.persistence.security.IOperationDAO;
import com.project.api.springusermanagement.repository.security.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OperationDAOImpl implements IOperationDAO {

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public List<Operation> findByPublicAccess() {
        return operationRepository.findByPublicAccess();
    }

}
