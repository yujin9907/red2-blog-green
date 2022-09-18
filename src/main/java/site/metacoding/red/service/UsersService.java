package site.metacoding.red.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.web.request.users.JoinDto;
import site.metacoding.red.web.request.users.LoginDto;
import site.metacoding.red.web.request.users.UpdateDto;

@RequiredArgsConstructor
@Service
public class UsersService {
	
	private final UsersDao usersDao;
	private final BoardsDao boardsDao;
	private final HttpSession session;
	
	public void 회원가입(JoinDto joinDto) { 
		Users usersPS = joinDto.toEntity(joinDto);
		usersDao.insert(usersPS);
	}
	public Users 로그인(LoginDto loginDto) { 
		Users usersPS = usersDao.findByUsername(loginDto.getUsername());
		if(usersPS==null) {
			return null;
		}
		
		if(usersPS.getPassword()==loginDto.getPassword()) {
			session.setAttribute("principal", usersPS);
		}
		return usersPS; 
	} 
	public Users 회원수정(Integer id, UpdateDto updateDto) {
		//1. 영속화
		Users usersPS = usersDao.findById(id);
		//2. 영속화된 객체 변경
		usersPS.setPassword(updateDto.getPassword());
		usersPS.setEmail(updateDto.getEmail());
//		//3. 디비수정
		usersDao.update(usersPS);
		
		return usersPS;
	}
	
	
	@Transactional(rollbackFor = RuntimeException.class) 
	public void 회원탈퇴(Integer id) {
		usersDao.deleteById(id);
		boardsDao.updateToUsersId(id);
	}

	public boolean 아이디중복확인(String username) {
		Users usersPS = usersDao.findByUsername(username);
		if(usersPS==null) {
			return false;
		}else {
			return true;
		}
	}
	
	public Users 회원정보보기(Integer id) {
		Users usersPS = usersDao.findById(id);
		return usersPS;
	}
}
