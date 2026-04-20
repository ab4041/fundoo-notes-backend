package com.fundoonotes.fundoo_notes_backend.dto.request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegisterRequestDto {

    @NotBlank
    private String firstName;

    @Email
    private String email;

    @NotBlank
    private String password;
}