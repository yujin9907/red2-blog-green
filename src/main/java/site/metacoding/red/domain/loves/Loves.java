package site.metacoding.red.domain.loves;


import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Loves {
    private Integer id;
    private Integer boardsId;
    private Integer usersId;
    private Timestamp createdAt;

    public void toEntity(Integer boardsId, Integer usersId){
        this.boardsId=boardsId;
        this.usersId=usersId;
    };
}
