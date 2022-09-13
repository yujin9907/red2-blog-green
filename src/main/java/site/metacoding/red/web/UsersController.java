package site.metacoding.red.web;

import java.awt.print.Printable;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.service.UsersService;
import site.metacoding.red.util.Script;
import site.metacoding.red.web.request.users.JoinDto;
import site.metacoding.red.web.request.users.LoginDto;
import site.metacoding.red.web.request.users.UpdateDto;

@RequiredArgsConstructor
@Controller
public class UsersController {

	private final UsersService usersService;
	private final HttpSession session;
	
	@GetMapping("/joinForm") // 인증과 관려된 애들은 도메인명을 붙이지 않음
	// 나중에 인증이 필요한 애들만 users를 붙이는 등 해서 xml을 통해 인증 주소세팅을 할 수 있음
	public String joinForm() {
		return "users/joinForm";
	}
	@GetMapping("/loginForm")
	public String loginForm() {
		return "users/loginForm";
	}
	
	// 회원 가입 하기
	@PostMapping("/join")
	public String join(JoinDto joinDto) {
		usersService.회원가입(joinDto);
		return "redirect:/loginForm"; // 매핑 주소를 리다렉션 시킴 
	}
	
	// 메시지 뿌리면서 가는 법
	@PostMapping("/login")
	public @ResponseBody String login(LoginDto loginDto) { // 자바스크립트 사용을 위해, 뷰리졸버 발동 안 함, 데이터를 리턴하는 메서드가 됨
		Users principal = usersService.로그인(loginDto);
		if(principal == null) {
			return Script.back("아이디 혹은 비밀번호가 틀렸습니다.");
		} // 안 된 경우를 처리하고 정상 경우는 밑에 두는 게 코드가 깔끔함
		session.setAttribute("principal", principal);
		return Script.href("/");  
	}
	
	@GetMapping("/uesrs/{id}")
	public String updateForm(@PathVariable Integer id, Model model) {
		Users usersPS = usersService.회원정보보기(id); // db에서 가져온건 ps 붙임
		model.addAttribute("usres", usersPS);
		return "users/updateForm"; // 이런 거 만드는 거 숙제임
	}
	
	@PutMapping("/uesrs/{id}")
	public String update(@PathVariable Integer id, UpdateDto updateDto) {
		usersService.회원수정(id, updateDto);
		return "redirect:/users/"+id;
	}
	
	@DeleteMapping("/users/{id}")
	public @ResponseBody String delete(@PathVariable Integer id) {
		usersService.회원탈퇴(id);
		return Script.href("/loginForm", "회원탈퇴 완료");
	}
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/loginForm";
	}
	
}