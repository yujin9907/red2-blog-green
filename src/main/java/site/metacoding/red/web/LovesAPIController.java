package site.metacoding.red.web;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.service.LovesService;
import site.metacoding.red.web.response.CMRespDto;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class LovesAPIController {

    private final HttpSession session;
    private final LovesService lovesService;

    @PostMapping("/api/loves")
    public CMRespDto<?> clickLove(@Param("boardsId") Integer boardsId){
        System.out.println("보드아이디 나옴?"+boardsId);

        Users users = (Users) session.getAttribute("principal");
        lovesService.좋아요클릭(boardsId, users.getId());
        return new CMRespDto<>(1,"성공",null);
    }

    @DeleteMapping("/api/loves")
    public void cancelLove(Integer boardsId){
        Users users = (Users) session.getAttribute("principal");
        lovesService.좋아요취소(boardsId, users.getId());
    }
}
