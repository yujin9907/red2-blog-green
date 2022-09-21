package site.metacoding.red.service;

import java.util.List;
import java.util.function.BiConsumer;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.loves.Loves;
import site.metacoding.red.domain.loves.LovesDao;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.handler.ex.MyException;
import site.metacoding.red.web.request.boards.WriteDto;
import site.metacoding.red.web.request.boards.UpdateDto;
import site.metacoding.red.web.response.boards.DetailDto;
import site.metacoding.red.web.response.boards.PagingDto;
import site.metacoding.red.web.response.boards.MainDto;

@RequiredArgsConstructor
@Service
public class BoardsService {

	private final BoardsDao boardsDao;
	private final HttpSession session;
	private final LovesDao lovesDao;

	public void 좋아요취소(Integer lovesId){
		lovesDao.delete(lovesId);
	}
	public Loves 좋아요(Loves loves){
		lovesDao.insert(loves);
		return loves;
	}

	public PagingDto 게시글목록보기(Integer page, String keyword) {

		if (page == null) {
			page = 0;
		}
		int startNum = page * 3;

		List<MainDto> boardsList = boardsDao.findSearch(startNum, keyword);
		PagingDto paging = boardsDao.paging(startNum, keyword);

		paging.setKeyword(keyword);
		paging.dopaging();
		paging.setMainDto(boardsList);
		return paging;
	}

	public Boards 게시글수정화면데이터가져오기(Integer id){

		Boards boards = boardsDao.findById(id);
		if(boards==null){
			throw new MyException(id+"의 게시글을 찾을 수 없습니다");
		}

		return boardsDao.findById(id);
	}

	public DetailDto 게시글상세보기(Integer boardsId, Integer principalId) {
		DetailDto detailDto = lovesDao.findByDetail(principalId, boardsId);
		return detailDto;
	}

	public void 게시글수정하기(Integer id, UpdateDto updateDto) {
		Boards boardsPS = boardsDao.findById(id);
		// 없는 보드 번호를 때렸을 때 예외처리
		if(boardsPS == null){
			throw new MyException(id+"의 게시글을 찾을 수 없습니다."); // ds에 캐치로넘겨버림
		}
		// 원리 : new runtime익셉션 -> 메모리에 스로우이셉션, 익셉션, 런타임익셉션(상속관계) 가 뜸. 부모가 가지고 있는 메세지 변수에 저걸 넘김
		// 그래서 이게 발동하면, mycexeptionhandler에 구현한 메서드 (exeption이 발동됨)
		boardsPS.update(updateDto);
		boardsDao.update(boardsPS);
	}

	public void 게시글삭제하기(Integer id) {
		Boards boardsPS = boardsDao.findById(id);
		if(boardsPS==null){
			return;
		}
		boardsDao.delete(id);
	}

	public void 게시글쓰기(WriteDto writeDto, Users principal) {
		boardsDao.insert(writeDto.toEntity(principal.getId()));
	}
}
