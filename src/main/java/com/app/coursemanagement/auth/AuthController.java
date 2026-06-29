package com.app.coursemanagement.auth;

import com.app.coursemanagement.common.ApiResponse;
import com.app.coursemanagement.dto.LoginRequest;
import com.app.coursemanagement.dto.LoginResponse;
import com.app.coursemanagement.dto.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<String>> signUp(@RequestBody SignupRequest signupRequest){
        authService.signUp(signupRequest);

        ApiResponse<String> res = new ApiResponse<>(
                true,
                "User Signup Successfully.",
                null
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(res);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> logIn(@RequestBody LoginRequest loginRequest){
         LoginResponse loginResponse = authService.logIn(loginRequest);

        ApiResponse<LoginResponse> res = new ApiResponse<>(
                true,
                "Login Successfull.",
                loginResponse
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }
}
