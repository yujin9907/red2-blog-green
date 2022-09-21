package site.metacoding.red.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import site.metacoding.red.handler.ex.MyApiException;
import site.metacoding.red.handler.ex.MyException;
import site.metacoding.red.util.Script;
import site.metacoding.red.web.response.CMRespDto;

@ControllerAdvice // 뷰도 있고 데이터도 있어서 그냥 controlleradvice / 데이터만 리턴하면 레스트어드바이스 하면 됨
public class MyExceptionHandler {
    @ExceptionHandler(MyApiException.class) // RuntimeException : 실행 중 오류, 그냥 익셉션은 컴파일 오류
    public @ResponseBody CMRespDto<?> apiError(Exception e){ // ds가 리플렉션으로 찾기 때문에 메서드명 노상관
        return new CMRespDto<>(-1, e.getMessage(), null);
    }
    // catch 구문 실행될 때, reuntime오류나면 이 메서드를 실행하도록

    @ExceptionHandler(MyException.class)
    public @ResponseBody String Error(Exception e){
        return Script.back(e.getMessage());
    }
}
