package lab4.lab4.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupUserDTO {
    private static final long serialVersionUID = 1234567890;

    @NotBlank(message="Firstname is can't be empty")
    private String firstName;

    @NotBlank(message="Lastname is can't be empty")
    private String lastName;

    @NotBlank(message="Email is can't be empty")
    @Email(message="Please enter a valid email address")
    private String email;
    @NotBlank(message="Password is required")
    @Size(min = 8, max = 24, message="Password must be at least 8 characters and not more than 24 characters")
    private String password;
}
