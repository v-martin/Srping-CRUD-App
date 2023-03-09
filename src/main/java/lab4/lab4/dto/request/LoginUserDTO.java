package lab4.lab4.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginUserDTO {
    private static final long serialVersionUID = 123456789;
    @NotBlank(message="Email is can't be empty")
    @Email(message="Please enter a valid email address")
    private String email;
    @NotBlank(message="Password is required")
    @Size(min = 8, max = 24, message="Password must be at least 8 characters and not more than 24 characters")
    private String password;
}