package site.metacoding.red.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class CMRespDto<T> {
	// 공통 응답 dto
	private Integer code; // 1 정상, -1 실패
	private String msg; // 실패 이유
	private T data; // 응답할 데이터, 실제 리턴 값, 당연히 항상 다름
}
