package com.project.api.springusermanagement.persistence.impl;

import com.project.api.springusermanagement.entities.Post;
import com.project.api.springusermanagement.persistence.IPostDAO;
import com.project.api.springusermanagement.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PostDAOImpl implements IPostDAO {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Optional<Post> findOneById(long postId) {
        return postRepository.findById(postId);
    }

    @Override
    public Post createOne(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post updateOne(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post disabledOne(Post post) {
        return postRepository.save(post);
    }

}
