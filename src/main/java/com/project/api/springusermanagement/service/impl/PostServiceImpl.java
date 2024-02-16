package com.project.api.springusermanagement.service.impl;

import com.project.api.springusermanagement.dto.PostDTO;
import com.project.api.springusermanagement.entities.security.User;
import com.project.api.springusermanagement.exception.ObjectNotFoundException;
import com.project.api.springusermanagement.entities.Post;
import com.project.api.springusermanagement.persistence.IPostDAO;
import com.project.api.springusermanagement.service.IPostService;
import com.project.api.springusermanagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    private IPostDAO postDAO;

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postDAO.findAll(pageable);
    }

    @Override
    public Optional<Post> findOneById(long postId) {
        return postDAO.findOneById(postId);
    }

    @Override
    public Post createOne(PostDTO postDto) {
        Post postEntity = Post.builder()
                .text(postDto.getText())
                .status(Post.PostStatus.ENABLED)
                .user(postDto.getUser())
                .creationDate(new Date())
                .build();

        return postDAO.createOne(postEntity);
    }

    @Override
    public Post updateOneById(long postId, PostDTO postDto) {
        Post postEntity = postDAO.findOneById(postId)
                .orElseThrow(() -> new ObjectNotFoundException("Post not found with id " + postId));

        postEntity.setText(postDto.getText());
        postEntity.setModificationDate(new Date());
        return postDAO.updateOne(postEntity);
    }

    @Override
    public Post disabledOneById(long postId) {
        Post postEntity = postDAO.findOneById(postId)
                .orElseThrow(() -> new ObjectNotFoundException("Post not found with id " + postId));

        postEntity.setStatus(Post.PostStatus.DISABLED);
        return postDAO.disabledOne(postEntity);
    }
}
