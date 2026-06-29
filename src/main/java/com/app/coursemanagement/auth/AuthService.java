package com.app.coursemanagement.auth;

import com.app.coursemanagement.dto.LoginRequest;
import com.app.coursemanagement.dto.LoginResponse;
import com.app.coursemanagement.dto.SignupRequest;
import com.app.coursemanagement.exception.InvalidCredentialsException;
import com.app.coursemanagement.exception.UserAlreadyExistsException;
import com.app.coursemanagement.exception.UserNotFoundException;
import com.app.coursemanagement.models.Role;
import com.app.coursemanagement.models.User;
import com.app.coursemanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(SignupRequest signupRequest){
        if(userRepository.existsByEmail(signupRequest.getEmail())){
            throw new UserAlreadyExistsException("User Already Exist With this Email");
        }

        User user = new User();
        user.setFullName(signupRequest.getFullName());
        user.setEmail(signupRequest.getEmail());

        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setRole(Role.STUDENT);

        userRepository.save(user);
    }

    public LoginResponse logIn(LoginRequest loginRequest) {
        Optional<User> res = userRepository.findByEmail(loginRequest.getEmail());
        if(res.isEmpty())
            throw new UserNotFoundException("User Not Found for this Email");

        User user = res.get();

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException("Invalid Credentials");
        }

        return new LoginResponse("LogIn Successfully");
    }
}
