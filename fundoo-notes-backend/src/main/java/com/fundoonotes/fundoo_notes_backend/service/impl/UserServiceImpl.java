package com.fundoonotes.fundoo_notes_backend.service.impl;

import com.fundoonotes.fundoo_notes_backend.dto.request.UserRegisterRequestDto;
import com.fundoonotes.fundoo_notes_backend.entity.User;
import com.fundoonotes.fundoo_notes_backend.repository.UserRepository;
import com.fundoonotes.fundoo_notes_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public String register(UserRegisterRequestDto dto) {

        User user = new User();

        user.setFirstName(dto.getFirstName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        userRepository.save(user);

        return "User registered successfully";
    }
}