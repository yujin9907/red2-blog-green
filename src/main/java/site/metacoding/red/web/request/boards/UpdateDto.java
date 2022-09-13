package site.metacoding.red.web.request.boards;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.red.domain.boards.Boards;

@Setter
@Getter
public class UpdateDto {
	private String title;
	private String content;
	
	public Boards toEntity(String title, String content) {
		Boards boards = new Boards(title, content);
		return boards;
	}
}
