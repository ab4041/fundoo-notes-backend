package com.fundoonotes.fundoo_notes_backend.controller;

import com.fundoonotes.fundoo_notes_backend.dto.request.LoginRequestDto;
import com.fundoonotes.fundoo_notes_backend.dto.request.UserRegisterRequestDto;
import com.fundoonotes.fundoo_notes_backend.dto.response.LoginResponseDto;
import com.fundoonotes.fundoo_notes_backend.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    // REGISTER API
    @PostMapping("/register")
    public String register(
            @RequestBody UserRegisterRequestDto dto
    ) {

        return userService.register(dto);

    }


    // LOGIN API
    @PostMapping("/login")
    public LoginResponseDto login(
            @RequestBody LoginRequestDto dto
    ) {

        return userService.login(dto);

    }

}