package lab4.lab4.dto.response;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseHitDTO {
    private int x;

    private float y;

    private int r;

    private boolean status;

    private String beginDate;

    private float codeTime;

}
