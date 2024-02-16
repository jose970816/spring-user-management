package com.project.api.springusermanagement.service;

import com.project.api.springusermanagement.dto.PostDTO;
import com.project.api.springusermanagement.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IPostService {

    Page<Post> findAll(Pageable pageable);

    Optional<Post> findOneById(long postId);

    Post createOne(PostDTO postDto);

    Post updateOneById(long postId, PostDTO postDto);

    Post disabledOneById(long postId);

}
