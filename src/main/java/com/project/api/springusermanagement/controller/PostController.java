package com.project.api.springusermanagement.controller;

import com.project.api.springusermanagement.dto.PostDTO;
import com.project.api.springusermanagement.entities.security.User;
import com.project.api.springusermanagement.exception.UnauthorizedException;
import com.project.api.springusermanagement.entities.Post;
import com.project.api.springusermanagement.service.IPostService;
import com.project.api.springusermanagement.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private IPostService postService;

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<Page<Post>> findAll(Pageable pageable){
        Page<Post> postsPage = postService.findAll(pageable);

        if(postsPage.hasContent())
            return ResponseEntity.ok(postsPage);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Post> createOne(@RequestBody @Valid PostDTO postDto) {
        //1. obtener del securirycontext el autentication
        UsernamePasswordAuthenticationToken unptAuth=(UsernamePasswordAuthenticationToken) SecurityContextHolder
                .getContext().getAuthentication();
        //2. Obtener usarname dentro del principal
        String username =(String)unptAuth.getPrincipal();
        Optional<User> userOptional = userService.findByUsername(username);
        postDto.setUser(userOptional.get());

        Post postResponse = postService.createOne(postDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(postResponse);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> updateOneById(@PathVariable long postId, @RequestBody @Valid PostDTO postDto, Principal principal) {
        Optional<Post> postOptional = postService.findOneById(postId);
        if (postOptional.isEmpty())
            return ResponseEntity.notFound().build();

        Post postExist = postOptional.get();
        // Verificar si el usuario actual es el propietario del post
        if (!postExist.getUser().getUsername().equals(principal.getName())) {
            throw new UnauthorizedException("El usuario " + principal.getName() + " no puede modificar el Post con id " + postId +" ya que otro usuario lo creó");
        }

        Post postResponse = postService.updateOneById(postId, postDto);
        return ResponseEntity.ok(postResponse);
    }

    @PutMapping("/{postId}/disabled")
    public ResponseEntity<Post> disabledOneById(@PathVariable long postId, Principal principal) {
        Optional<Post> postOptional = postService.findOneById(postId);
        if (postOptional.isEmpty())
            return ResponseEntity.notFound().build();

        Post postExist = postOptional.get();
        // Verificar si el usuario actual es el propietario del post
        if (!postExist.getUser().getUsername().equals(principal.getName())) {
            throw new UnauthorizedException("El usuario " + principal.getName() + " no puede deshabilitar el Post con id " + postId +" ya que otro usuario lo creó");
        }

        Post postResponse = postService.disabledOneById(postId);
        return ResponseEntity.ok(postResponse);
    }

}
