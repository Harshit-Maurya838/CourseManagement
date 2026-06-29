package com.app.coursemanagement.dto;

import lombok.Getter;

@Getter
public class SignupRequest {
    private String fullName;
    private String email;
    private String password;
}
