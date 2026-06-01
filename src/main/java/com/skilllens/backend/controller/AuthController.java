package com.skilllens.backend.controller;

import com.skilllens.backend.dto.AuthResponse;
import com.skilllens.backend.security.JwtUtil;
import com.skilllens.backend.dto.LoginRequest;
import java.util.Optional;
import com.skilllens.backend.dto.RegisterRequest;
import com.skilllens.backend.model.User;
import com.skilllens.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "Email already exists";
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // Encrypt password
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        userRepository.save(user);

        return "User Registered Successfully";
    }

   @PostMapping("/login")
public Object login(@RequestBody LoginRequest request) {

    Optional<User> userOptional =
            userRepository.findByEmail(request.getEmail());

    if (userOptional.isEmpty()) {
        return "User not found";
    }

    User user = userOptional.get();

    if (!passwordEncoder.matches(
            request.getPassword(),
            user.getPassword())) {

        return "Invalid Password";
    }

    String token =
            JwtUtil.generateToken(user.getEmail());

    return new AuthResponse(token);
}
 }
