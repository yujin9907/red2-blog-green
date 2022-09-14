package site.metacoding.red.web.response.boards;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainDto {
	private Integer id;
	private String title;
	private String username;
	private PagingDto paging; // 컴포지션
}
