package com.fundoonotes.fundoo_notes_backend.dto.request;

import lombok.Data;

@Data
public class LoginRequestDto {

    private String email;

    private String password;

}