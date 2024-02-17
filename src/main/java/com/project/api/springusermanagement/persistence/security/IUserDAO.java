package com.project.api.springusermanagement.persistence.security;

import com.project.api.springusermanagement.entities.security.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {

    Page<User> findAll(Pageable pageable);

    List<User> findAll();

    Optional<User> findOneById(long userId);

    Optional<User> findByUsername(String username);

    User createOne(User user);

    User updateOne(User user);

    User disabledOne(User user);

}
