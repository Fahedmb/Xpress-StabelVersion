package com.glsi.xpress.DTO;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LoginDTO {
    @NotNull
    @NotBlank @NotEmpty
    private String username;
    @NotNull @NotBlank @NotEmpty
    private String password;
}
