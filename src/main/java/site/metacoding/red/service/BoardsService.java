package site.metacoding.red.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.web.dto.response.boards.MainDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;
import site.metacoding.red.web.request.boards.InsertDto;
import site.metacoding.red.web.request.boards.UpdateDto;
import site.metacoding.red.web.response.boards.PagingDto;

@RequiredArgsConstructor
@Service
public class BoardsService {
	
	private final BoardsDao boardsDao;
	private final HttpSession session;
	
	public void 게시글목록보기(Integer page, String keyword) {
		if(page==null) {
			page=0;
		}
		int startNum = page * 3;
		
		if (keyword == null || keyword.isEmpty()) {
			
			List<Boards> boardsList = boardsDao.findAll();

			PagingDto paging = boardsDao.paging();
			
			Integer currentPage = paging.getCurrentPage();
			Integer totalPage = paging.getTotalPage();
			Integer count = 5;
			Integer start = ((currentPage / count) * count) + 1;
			Integer block = (currentPage / count) + 1;
			Integer last = ((currentPage / count) + 1) * count;

			if (paging.getTotalPage() < last) {
				last = paging.getTotalPage();
			}

			paging.setKeyword(keyword);
			paging.setStartPageNum(start);
			paging.setLastPageNum(last);
			paging.setBlockPage(block);
			paging.setBlockPageCount(count);

			model.addAttribute("paging", paging);
			model.addAttribute("boardsList", boardsList);



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
	public void 게시글쓰기(InsertDto insertDto) {
		Users principalUser = (Users) session.getAttribute("principal");
		boardsDao.insert(insertDto, principalUser.getId());
	}
}
