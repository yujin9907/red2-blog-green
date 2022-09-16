package site.metacoding.red.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.web.request.boards.WriteDto;
import site.metacoding.red.web.request.boards.UpdateDto;
import site.metacoding.red.web.response.boards.PagingDto;
import site.metacoding.red.web.response.boards.MainDto;

@RequiredArgsConstructor
@Service
public class BoardsService {

	private final BoardsDao boardsDao;
	private final HttpSession session;

	public PagingDto 게시글목록보기(Integer page, String keyword) {

		if (page == null) {
			page = 0;
		}
		int startNum = page * 3;

		if (keyword == null || keyword.isEmpty()) {

			List<MainDto> boardsList = boardsDao.findAll(startNum);
			PagingDto paging = boardsDao.paging(page, null);

			paging.dopaging();
			paging.setMainDto(boardsList);
			
			System.out.println(paging.isFirst());
			System.out.println(paging.getStartPageNum());

			return paging;

		} else {

			List<MainDto> boardsList = boardsDao.findSearch(startNum, keyword);
			PagingDto paging = boardsDao.paging(page, keyword);

			paging.dopaging();
			paging.setMainDto(boardsList);

			return paging;
		}

	}

	public Boards 게시글상세보기(Integer id) {
		Boards boardsPS = boardsDao.findById(id);
		return boardsPS;
	}

	public void 게시글수정하기(Integer id, UpdateDto updateDto) {
		Boards boardsPS = boardsDao.findById(id);
		boardsPS.update(updateDto);
		boardsDao.update(boardsPS);
	}

	public void 게시글삭제하기(Integer id) {
		boardsDao.deleteById(id);
	}

	public void 게시글쓰기(WriteDto writeDto, Users principal) {
		boardsDao.insert(writeDto.toEntity(principal.getId()));
	}
}
