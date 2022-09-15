package site.metacoding.red.web.request.boards;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.red.domain.boards.Boards;

@Setter
@Getter
public class WriteDto {
	private String title;
	private String content;
	
	public Boards toEntity(Integer usersid) {
		Boards boards = new Boards(this.title, this.content, usersid);
		return boards;
	}
}
