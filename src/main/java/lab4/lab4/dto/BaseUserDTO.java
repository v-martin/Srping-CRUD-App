package lab4.lab4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseUserDTO {
    private Long id;
    private String email;
    private String firstname;
    private String lastname;
}
