package com.project.api.springusermanagement.service;

import com.project.api.springusermanagement.dto.UserDTO;
import com.project.api.springusermanagement.entities.security.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IUserService {

    Page<User> findAll(Pageable pageable);

    Optional<User> findOneById(long userId);

    Optional<User> findByUsername(String username);

    User createOne(UserDTO userDTO);

    User updateOneById(long userId, UserDTO userDTO);

    User disabledOneById(long userId);

}
