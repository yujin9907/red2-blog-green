package site.metacoding.red.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.service.LovesService;
import site.metacoding.red.web.request.loves.InsertDto;
import site.metacoding.red.web.response.CMRespDto;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class LovesController {

    private final HttpSession session;
    private final LovesService lovesService;

    @PostMapping("/loves/{boardsId}")
    public @ResponseBody
    CMRespDto<?> clickLove(@PathVariable Integer boardsId){
        // System.out.println("보드아이디 나옴?"+insertDto.getBoardsId());

        Users users = (Users) session.getAttribute("principal");
        lovesService.좋아요클릭(boardsId, users.getId());
        return new CMRespDto<>(1,"성공",null);
    }

    @DeleteMapping("/loves/{boardsId}")
    public @ResponseBody CMRespDto<?> cancelLove(@PathVariable Integer boardsId){
        Users users = (Users) session.getAttribute("principal");
        lovesService.좋아요취소(boardsId, users.getId());
        return new CMRespDto<>(1, "삭제성공", null);
    }
}
