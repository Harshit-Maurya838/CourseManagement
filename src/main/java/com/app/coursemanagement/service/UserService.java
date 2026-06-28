package com.app.coursemanagement.service;

import com.app.coursemanagement.exception.UserAlreadyExistsException;
import com.app.coursemanagement.models.User;
import com.app.coursemanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistsException("Email already exists");
        }

        return userRepository.save(user);
    }
}
