package com.project.api.springusermanagement.controller;

import com.project.api.springusermanagement.dto.UserDTO;
import com.project.api.springusermanagement.entities.security.User;
import com.project.api.springusermanagement.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/listAll")
    public ResponseEntity<Page<User>> findAll(Pageable pageable){
        Page<User> usersPage = userService.findAll(pageable);

        if(usersPage.hasContent())
            return ResponseEntity.ok(usersPage);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<User> findAllByUsername(Principal principal){
        Optional<User> usersPage = userService.findByUsername(principal.getName());

        return usersPage.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<User> createOne(@RequestBody @Valid UserDTO userDTO) {
        User userResponse = userService.createOne(userDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateOneById(@PathVariable long userId, @RequestBody @Valid UserDTO userDto) {
        User userResponse = userService.updateOneById(userId, userDto);
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("/{userId}/disabled")
    public ResponseEntity<User> disabledOneById(@PathVariable long userId) {
        User userResponse = userService.disabledOneById(userId);
        return ResponseEntity.ok(userResponse);
    }
}
