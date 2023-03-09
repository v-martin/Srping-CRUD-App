package lab4.lab4.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MainInfoDTO {
    private String email;
    private String firstname;
    private String lastname;
}