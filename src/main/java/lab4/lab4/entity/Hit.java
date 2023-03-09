package lab4.lab4.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "hits")
public class Hit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Hit(int x, float y, int r, boolean status, String beginDate, float codeTime, Long userId) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.status = status;
        this.beginDate = beginDate;
        this.codeTime = codeTime;
        this.userId = userId;
    }

    private int x;

    private float y;

    private int r;

    private boolean status;

    private String beginDate;

    private float codeTime;

    private long userId;
}
