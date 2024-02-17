package com.project.api.springusermanagement.persistence.impl.security;

import com.project.api.springusermanagement.entities.security.User;
import com.project.api.springusermanagement.persistence.security.IUserDAO;
import com.project.api.springusermanagement.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDAOImpl implements IUserDAO {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findOneById(long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User createOne(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateOne(User user) {
        return userRepository.save(user);
    }

    @Override
    public User disabledOne(User user) {
        return userRepository.save(user);
    }

}
