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
import site.metacoding.red.domain.loves.Loves;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.service.BoardsService;
import site.metacoding.red.web.request.boards.UpdateDto;
import site.metacoding.red.web.request.boards.WriteDto;
import site.metacoding.red.web.response.CMRespDto;
import site.metacoding.red.web.response.boards.DetailDto;
import site.metacoding.red.web.response.boards.PagingDto;

import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@Controller
public class BoardsController {

	private final HttpSession session;
	private final BoardsService boardsService;

	// 인증필요
	@PutMapping("/s/api/boards/{id}")
	public @ResponseBody CMRespDto<?> update(@PathVariable Integer id, @RequestBody UpdateDto updateDto) {
		boardsService.게시글수정하기(id, updateDto);
		return new CMRespDto<>(1, "수정완료", null);
	}
	// 인증필요
	@GetMapping("/s/boards/{id}/updateForm")
	public String updateForm(@PathVariable Integer id, Model model) {
		// 여기서 오류 확인할 수 없음 db에서 select해 봐야 아이디가 잘못된지 확인할 수 있음
		Boards boardsPS = boardsService.게시글수정화면데이터가져오기(id);
		model.addAttribute("boards", boardsPS);
		return "boards/updateForm";
	}
	// 인증필요
	@DeleteMapping("/s/api/boards/{id}")
	public @ResponseBody CMRespDto<?> deleteBoards(@PathVariable Integer id) {
		boardsService.게시글삭제하기(id);
		return new CMRespDto<>(1, "삭제성공", null);
	}
	// 인증필요
	@PostMapping("/s/api/boards")
	public @ResponseBody CMRespDto<?> writeBoards(@RequestBody WriteDto writeDto) {
		Users principal = (Users) session.getAttribute("principal");
		boardsService.게시글쓰기(writeDto, principal);
		return new CMRespDto<>(1, "작성완료", null);
	}

	@GetMapping({ "/", "/boards" })
	public String getBoardList(Model model, @Param(value="page") Integer page, @Param(value="keyword") String keyword) { // 0 -> 0, 1->10, 2->20
		PagingDto pagingDto = boardsService.게시글목록보기(page, keyword);
		model.addAttribute("pagingDto", pagingDto);

		// 응답하면 리퀘스트 객체는 사라짐. 세션은 안 사라짐

		// dto 안만들고 넘기기

		Map<String, Object> referer = new HashMap<>();
		referer.put("page", pagingDto.getCurrentPage());
		referer.put("keyword", pagingDto.getKeyword());

		session.setAttribute("referer", referer);


		return "boards/main";
	}

	@GetMapping("/boards/{id}")
	public String getBoardDetail(@PathVariable Integer id, Model model) {
		Users principal = (Users) session.getAttribute("principal");
		DetailDto detailDto;
		if(principal==null){
			detailDto = boardsService.게시글상세보기(id, null);
		} else {
			detailDto = boardsService.게시글상세보기(id, principal.getId());
		}
		model.addAttribute("detailDto", detailDto);
		return "boards/detail";
	}
	// 인증필요
	@GetMapping("/s/boards/writeForm")
	public String writeForm() {
		Users principal = (Users) session.getAttribute("principal");
		if (principal == null) {
			return "redirect:/loginForm";
		}
		return "boards/writeForm";
	}
	// 인증필요
	@PostMapping("/s/api/boards/{id}/loves") // 게시글 id번을 좋아요 하겠다
	public @ResponseBody CMRespDto<?> insertLoves(@PathVariable Integer id){ // 주소규칙보다 가독성을 우선시해서
		// boardsid는 패스로, usersid는 세션에 있으므로 바디 굳이 받아주지 않음
		Users principal = (Users) session.getAttribute("principal");
		Loves loves = new Loves(id, principal.getId()); // 이건 규칙, 객체를 생성해서 전달(디티오를 안 만들어도 됨)
		boardsService.좋아요(loves); // 러브아이디의 기본키를 받아와야 삭제할 수 있기 때문에
		return new CMRespDto<>(1, "성공", loves);

		// 이런 건 회사마다 스타일에 맞춰서 작성하면 됨
	}
	// 인증 필요
	@DeleteMapping("/s/api/boards/{id}/loves/{lovesId}")
	public @ResponseBody CMRespDto<?> deleteLove(@PathVariable Integer id, @PathVariable Integer lovesId){
		boardsService.좋아요취소(lovesId);
		return new CMRespDto<>(1, "성공", null);
	}
}








