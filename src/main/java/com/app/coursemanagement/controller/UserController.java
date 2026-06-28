package com.app.coursemanagement.controller;

import com.app.coursemanagement.common.ApiResponse;
import com.app.coursemanagement.models.User;
import com.app.coursemanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user){
        User createdUser = userService.createUser(user);
        ApiResponse<User> response = new ApiResponse<>(
                true,
                "User created successfully",
                createdUser
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
