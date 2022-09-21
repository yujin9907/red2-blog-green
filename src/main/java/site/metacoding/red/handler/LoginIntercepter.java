package site.metacoding.red.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.web.response.CMRespDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class LoginIntercepter implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uri = request.getRequestURI();

        HttpSession session = request.getSession();
        Users principal = (Users) session.getAttribute("principal");
        if (principal == null) {
            if (uri.contains("api")) {
                response.setContentType("application/json; charset=utf-8");
                PrintWriter out = response.getWriter();
                CMRespDto<?> cmRespDto = new CMRespDto<>(-1, "인증이 필요합니다", null);
                ObjectMapper om = new ObjectMapper();
                String json = om.writeValueAsString(cmRespDto);
                out.println(json);
            } else {
                response.sendRedirect("/loginForm");
            }
            return false;
        }
        return true;
    }
}
