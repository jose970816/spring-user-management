package com.project.api.springusermanagement.service.impl;

import com.project.api.springusermanagement.dto.UserDTO;
import com.project.api.springusermanagement.entities.Post;
import com.project.api.springusermanagement.exception.InvalidPasswordException;
import com.project.api.springusermanagement.exception.ObjectNotFoundException;
import com.project.api.springusermanagement.entities.security.Role;
import com.project.api.springusermanagement.entities.security.User;
import com.project.api.springusermanagement.persistence.security.IUserDAO;
import com.project.api.springusermanagement.service.IPostService;
import com.project.api.springusermanagement.service.IRoleService;
import com.project.api.springusermanagement.service.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPostService postService;

    public Page<User> findAll(Pageable pageable) {
        return userDAO.findAll(pageable);
    }

    @Override
    public Optional<User> findOneById(long userId) {
        return userDAO.findOneById(userId);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Transactional
    @Override
    public User createOne(UserDTO userDTO) {
        validatePassword(userDTO);

        User user = User.builder()
                .username(userDTO.getUsername())
                .name(userDTO.getName())
                .lastName(userDTO.getLastName())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .cellphone(userDTO.getCellphone())
                .enabled(true)
                .creationDate(new Date())
                .build();

        Role defaultRole = roleService.findDefaultRole().orElseThrow(() -> new ObjectNotFoundException("User not found. Default User"));
        user.setRole(defaultRole);

        User userEntity = userDAO.createOne(user);

        List<Post> postsList = new ArrayList<>();

        userDTO.getPosts().forEach(p -> {
            p.setUser(userEntity);
            p.setStatus(Post.PostStatus.ENABLED);
            p.setCreationDate(new Date());
            postsList.add(postService.createOne(p));
        });

        userEntity.setPosts(postsList);
        return userEntity;
    }

    @Override
    public User updateOneById(long userId, UserDTO userDTO) {
        User userEntity = userDAO.findOneById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found with id " + userId));

        userEntity.setUsername(userDTO.getUsername());
        userEntity.setName(userDTO.getName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setCellphone(userDTO.getCellphone());
        userEntity.setModificationDate(new Date());

        return userDAO.updateOne(userEntity);
    }

    @Override
    public User disabledOneById(long userId) {
        User userEntity = userDAO.findOneById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found with id " + userId));

        userEntity.setEnabled(false);
        return userDAO.disabledOne(userEntity);
    }

    private void validatePassword(UserDTO userDTO) {
        if(!StringUtils.hasText(userDTO.getPassword()) || !StringUtils.hasText(userDTO.getRepeatedPassword()))
            throw new InvalidPasswordException("Passwords don't match");

        if(!userDTO.getPassword().equals(userDTO.getRepeatedPassword()))
            throw new InvalidPasswordException("Passwords don't match");
    }

}
