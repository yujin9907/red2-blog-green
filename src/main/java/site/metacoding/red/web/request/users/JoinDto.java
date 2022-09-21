package site.metacoding.red.web.request.users;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.red.domain.users.Users;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class JoinDto {
	@Size(min=2, max=20, message = "길이가 부적합합니다.")
	@NotBlank(message = "username이 null 이거나 공백일 수 없음") // 널 공백 둘 다 검사, 사실 널 안 받으려면 db에도 not null
	private String username;
	@Size(min=2, max=20, message = "길이가 부적합합니다.")
	@NotBlank(message = "username이 null 이거나 공백일 수 없음")
	private String password;
	@Size(min=4, max=50, message = "길이가 부적합합니다.")
	@NotBlank(message = "username이 null 이거나 공백일 수 없음")
	private String email;
	
	public Users toEntity(JoinDto joinDto) {
		Users users = new Users(this.username, this.password, this.email);
		return users;
	}
}
