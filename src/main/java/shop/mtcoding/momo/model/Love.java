package shop.mtcoding.momo.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Love {
    private Integer id;
    private Integer boardId;
    private Integer userId;
    private Timestamp createdAt;
}
