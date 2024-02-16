package com.project.api.springusermanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.api.springusermanagement.entities.Post;
import com.project.api.springusermanagement.entities.security.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO implements Serializable {

    private Long id;

    @NotBlank
    private String text;

    @Enumerated(EnumType.STRING)
    private Post.PostStatus status;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date creationDate;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date modificationDate;

    private User user;

    public static enum PostStatus{
        ENABLED, DISABLED
    }

}
