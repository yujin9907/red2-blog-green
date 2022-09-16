package site.metacoding.red.web;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.service.BoardsService;
import site.metacoding.red.web.request.boards.UpdateDto;
import site.metacoding.red.web.request.boards.WriteDto;
import site.metacoding.red.web.response.CMRespDto;
import site.metacoding.red.web.response.boards.PagingDto;


@RequiredArgsConstructor
@Controller
public class BoardsController {

	private final HttpSession session;
	private final BoardsService boardsService;
	
	/***
	 * 
	 *     인증과 권한 체크는 지금 하지 마세요!!
	 */
	
	@PutMapping("/boards/{id}")
	public @ResponseBody CMRespDto<?> update(@PathVariable Integer id, @RequestBody UpdateDto updateDto) {
		boardsService.게시글수정하기(id, updateDto);
		return new CMRespDto<>(1, "수정완료", null);
	}

	@GetMapping("/boards/{id}/updateForm")
	public String updateForm(@PathVariable Integer id, Model model) {
		Boards boardsPS = boardsService.게시글상세보기(id);
		model.addAttribute("boards", boardsPS);
		return "boards/updateForm";
	}

	@DeleteMapping("/boards/{id}")
	public @ResponseBody CMRespDto<?> deleteBoards(@PathVariable Integer id) {
		boardsService.게시글삭제하기(id);
		return new CMRespDto<>(1, "삭제성공", null);
	}

	@PostMapping("/boards")
	public @ResponseBody CMRespDto<?> writeBoards(@RequestBody WriteDto writeDto) {
		Users principal = (Users) session.getAttribute("principal");
		boardsService.게시글쓰기(writeDto, principal);
		return new CMRespDto<>(1, "작성완료", null);
	}

	@GetMapping({ "/", "/boards" })
	public String getBoardList(Model model, @Param(value="page") Integer page, @Param(value="keyword") String keyword) { // 0 -> 0, 1->10, 2->20
		PagingDto pagingDto = boardsService.게시글목록보기(page, keyword);
		System.out.println(pagingDto.isFirst());
		model.addAttribute("pagingDto", pagingDto);
		return "boards/main";
	}

	@GetMapping("/boards/{id}")
	public String getBoardDetail(@PathVariable Integer id, Model model) {
		model.addAttribute("boards", boardsService.게시글상세보기(id));
		return "boards/detail";
	}

	@GetMapping("/boards/writeForm")
	public String writeForm() {
		Users principal = (Users) session.getAttribute("principal");
		if (principal == null) {
			return "redirect:/loginForm";
		}
		return "boards/writeForm";
	}
}








