package site.metacoding.red.domain.loves;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class Loves {
    private Integer id;
    private Integer boardsId;
    private Integer usersId;
    private Timestamp createdAt;

    public Loves(int boardsId, Integer usersId){
        this.boardsId=boardsId;
        this.usersId=usersId;
    };
}
