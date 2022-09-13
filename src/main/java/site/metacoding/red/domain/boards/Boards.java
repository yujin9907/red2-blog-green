package site.metacoding.red.domain.boards;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.red.web.request.boards.UpdateDto;

// 2. 게터세터
@NoArgsConstructor
@Getter
@Setter // 원칙은 엔티티에 세터를 사용하지 않음. 엔티티 변경을 위해서는 메서드를 따로 만들어야 됨
public class Boards {
	// 1. 엔티티 생성
	private Integer id;
	private String title;
	private String content;
	private Integer usersId;
	private Timestamp createdAt; // 작성 규칙 at 시분초 다 표현할 때, dt 년원일 표현할 때
	
	public Boards(String title, String content){
		this.title=title;
		this.content=content;
	}
	public void update(UpdateDto updateDto) {
		this.title=updateDto.getTitle();
		this.content=updateDto.getContent();
	}
}
