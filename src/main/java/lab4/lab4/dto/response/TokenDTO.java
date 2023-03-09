package lab4.lab4.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenDTO {

    private final String type = "Bearer";
    private String accessToken;
    private String refreshToken;

}