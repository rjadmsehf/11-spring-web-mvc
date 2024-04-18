package com.ohgiraffers.interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component //필기 등록된 빈을 가져오는 역할
public class StopWatchInterceptor implements HandlerInterceptor {

    private final MenuService menuService;

    @Autowired //필기 MenuService 의 객체를 가져오는 역할
    public StopWatchInterceptor(MenuService menuService) {
        this.menuService = menuService;
    }

    /* 필기 전처리 메소드 : 컨트롤러가 동작하기 전에 수행한다. (1) */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("preHandler 호출함...");

        long startTime = System.currentTimeMillis();

        request.setAttribute("startTime", startTime);

        /* 필기 true 이면 컨트롤러를 이어서 호출하게 한다. false 면 핸들러 메소드를 호출하지 않는다. */
        return true;
    }

    /* 필기 후처리 메소드 : 컨트롤러 동작 후 (2)*/
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        System.out.println("postHandler 호출함...");

        long startTime = (long) request.getAttribute("startTime");

        long endTime = System.currentTimeMillis();

        modelAndView.addObject("interval", endTime - startTime);
        //필기 modelAndView 에 계산 결과를 interval 키값에 담아서 html. 파일로 보내주는 역할

    }

    /* 필기  마지막에 호출하는 메소드 (3)*/
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        System.out.println("afterComplate 호출함...");

        menuService.method(); //필기 ("Service 메소드 호출 확인 ") 이라는 출력문 가져오기
    }
}