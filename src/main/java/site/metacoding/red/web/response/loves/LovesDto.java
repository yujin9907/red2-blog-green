package site.metacoding.red.web.response.loves;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LovesDto {
    private Integer lovedsId;
    private boolean checkLove;
    private Integer count;
}
