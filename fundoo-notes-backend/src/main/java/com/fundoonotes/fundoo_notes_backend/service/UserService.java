package com.fundoonotes.fundoo_notes_backend.service;

import com.fundoonotes.fundoo_notes_backend.dto.request.UserRegisterRequestDto;

public interface UserService {

    String register(UserRegisterRequestDto dto);

}