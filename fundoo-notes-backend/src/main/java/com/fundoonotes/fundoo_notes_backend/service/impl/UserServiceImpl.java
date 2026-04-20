package com.fundoonotes.fundoo_notes_backend.service.impl;

import com.fundoonotes.fundoo_notes_backend.dto.request.LoginRequestDto;
import com.fundoonotes.fundoo_notes_backend.dto.request.UserRegisterRequestDto;
import com.fundoonotes.fundoo_notes_backend.dto.response.LoginResponseDto;
import com.fundoonotes.fundoo_notes_backend.entity.User;
import com.fundoonotes.fundoo_notes_backend.repository.UserRepository;
import com.fundoonotes.fundoo_notes_backend.service.UserService;
import com.fundoonotes.fundoo_notes_backend.util.JwtUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;


    // REGISTER USER
    @Override
    public String register(UserRegisterRequestDto dto) {

        User user = new User();

        user.setFirstName(dto.getFirstName());
        user.setEmail(dto.getEmail());

        // Encrypt password before saving
        user.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );

        userRepository.save(user);

        return "User registered successfully";
    }


    // LOGIN USER
    @Override
    public LoginResponseDto login(LoginRequestDto dto) {

        User user = userRepository
                .findByEmail(dto.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        // Check password match
        if (!passwordEncoder.matches(
                dto.getPassword(),
                user.getPassword()
        )) {

            throw new RuntimeException("Invalid credentials");
        }

        // Generate JWT token
        String token = jwtUtil.generateToken(user.getId());

        return new LoginResponseDto(
                token,
                "Login successful"
        );
    }

}