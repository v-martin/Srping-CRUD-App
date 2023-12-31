package lab4.lab4.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateHitDTO {
    @NotNull(message="X value can't be empty")
    @DecimalMin(value="-5", message="Y value must be from -5 to 5")
    @DecimalMax(value="3" ,message="Y value must be from -5 to 5")
    private float x;
    @NotNull(message="Y value can't be empty")
    @DecimalMin(value="-5", message="Y value must be from -5 to 5")
    @DecimalMax(value="3" ,message="Y value must be from -5 to 5")
    private float y;
    @NotNull(message="R value can't be empty")
    @Min(value=1, message="R value must be from 1 to 5")
    @Max(value=5, message="R value must be from 1 to 5")
    private int r;
}