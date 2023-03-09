package lab4.lab4.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoDTO {
    private String accessToken;
    private String refreshToken;
    private final String type = "Bearer";
    private Long id;
    private String email;
    private String firstname;
    private String lastname;
}
