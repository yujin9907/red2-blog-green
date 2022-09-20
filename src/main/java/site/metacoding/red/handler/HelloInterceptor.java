package site.metacoding.red.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class HelloInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuilder s = new StringBuilder();
        BufferedReader br = request.getReader();
        while (true){
            s.append(br.readLine());
            if(br.readLine()==null){
                s.append(br.readLine());
                break;
            }
        }
        if(s.toString().contains("바보")){
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = response.getWriter();
            ObjectMapper om = new ObjectMapper();
            String json = om.writeValueAsString("바보아니야...");
            out.println(json);
        } else {
            System.out.println("잘못");
        }


        return false;
    }
}
