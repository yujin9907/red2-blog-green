package site.metacoding.red.web.response.loves;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GroupByDto {
    int loveCount;
//    boolean love;
    // 여기다가 love를 넣으면 안 되고
    // 그 좋아요를 눌렀는지 안 눌렀는지 체크하는 걸 별도로 구성해줘야됨
    // 마리에 블리언이 있으면 이것도 같이 넘겨서 체크하도록해야되나 시발
    // 그냥 1,0으로 구분하기만한대
}
