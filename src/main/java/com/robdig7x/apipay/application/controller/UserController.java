package com.robdig7x.apipay.application.controller;

import com.robdig7x.apipay.domain.model.entity.Users;
import com.robdig7x.apipay.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users users) {
        Users createdUsers = userService.createUser(users);
        return ResponseEntity.ok(createdUsers);
    }
}