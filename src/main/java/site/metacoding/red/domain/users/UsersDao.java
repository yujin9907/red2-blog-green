package site.metacoding.red.domain.users;

import java.util.List;

import site.metacoding.red.web.request.users.UpdateDto;



// 보드에서 작성한 거 그대로 복사
public interface UsersDao {
	public void insert(Users users); 
	public List<Users> findAll();
	public Users findById(Integer id);
	public void deleteById(Integer id);
	public Users findByUsername(String username);
	public Users update(Users users);
}
