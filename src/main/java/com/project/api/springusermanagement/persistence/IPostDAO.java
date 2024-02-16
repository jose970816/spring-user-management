package com.project.api.springusermanagement.persistence;

import com.project.api.springusermanagement.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IPostDAO {

    Page<Post> findAll(Pageable pageable);

    Optional<Post> findOneById(long postId);

    Post createOne(Post post);

    Post updateOne(Post post);

    Post disabledOne(Post post);

}
