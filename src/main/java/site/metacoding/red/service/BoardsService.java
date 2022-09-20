package site.metacoding.red.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.loves.Loves;
import site.metacoding.red.domain.loves.LovesDao;
import site.metacoding.red.domain.users.Users;
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
		return boardsDao.findById(id);
	}

	public DetailDto 게시글상세보기(Integer boardsId, Integer principalId) {
		DetailDto detailDto = lovesDao.findByDetail(principalId, boardsId);
		return detailDto;
	}

	public void 게시글수정하기(Integer id, UpdateDto updateDto) {
		Boards boardsPS = boardsDao.findById(id);
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
