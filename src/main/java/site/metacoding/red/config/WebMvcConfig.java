package site.metacoding.red.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import site.metacoding.red.handler.LoginIntercepter;

@Configuration // 설정파일 등록, 서버 실행될 때 메모리에 뜸
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 재정의하는 것
        registry.addInterceptor(new LoginIntercepter()) // 핸들러인터셉터 타입
                .addPathPatterns("/s/**");  // 어떤 주소일때 인터셉터 동작? -> 인증이 필요한 주소만
                // /board/1, 이런 /s/* -- /board/1/1, 이런 것도 실행하려면 ** 해야 됨
//                .addPathPatterns("/admin/**")
//                .excludePathPatterns("/s/boards/**"); // 특정주소는 제외

//        registry.addInterceptor(new HelloInterceptor())
//                .addPathPatterns("/**");
    }
}
