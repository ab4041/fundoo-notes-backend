package com.fundoonotes.fundoo_notes_backend.service;

import com.fundoonotes.fundoo_notes_backend.dto.request.LoginRequestDto;
import com.fundoonotes.fundoo_notes_backend.dto.request.UserRegisterRequestDto;
import com.fundoonotes.fundoo_notes_backend.dto.response.LoginResponseDto;

public interface UserService {

    String register(UserRegisterRequestDto dto);

    LoginResponseDto login(LoginRequestDto dto);

}