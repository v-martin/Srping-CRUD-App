package lab4.lab4.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class AllHitsDTO {
    private List<ResponseHitDTO> data;
}