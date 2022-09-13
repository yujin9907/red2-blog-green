package site.metacoding.red.domain.boards;

import java.util.List;

//3. 다오 생성 : 인터페이스
public interface BoardsDao {
	public void insert(Boards boards); // 1. crud 작성순서 전부 보이드에 매개변수 없이 일단 작성, 인서트 먼저, 인서트보단 세이브를 많이 쓰긴 함
	public List<Boards> findAll(); // 2. 조회 두가지
	public Boards findById(Integer id);
	public void update(Boards boards); // 3. ud
	public void deleteById(Integer id);
	
	public Boards findByUsersId(Integer usersId);
	public void updateToUsersId(Integer id);
	// 4. 세가지 작성하고, 매개변수에 뭐가 들어갈지 생각해보기
	// insert : 세가지만 받아야함 -> dto 만들기(컨트룰러부터 받는 것) 단, 유저스아이디는 세션값임 주의
	// 이런 식으로 매개변수 일단 설정
	
	// 5. 반환타입 적기
	// findbyid같은 경우 join 필요, 일단 boards로 하고 나중에 수정
}
