package com.ohgiraffers.exceptionhandler;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/* 필기.
    전역에 대한 예외 처리를 담당하는 친구이다.
    여러 컨트롤러에서 발생할 수 있느 예외(Exception)을 한곳에서 처리할 수 있다.
    코드의 중복을 줄이고 하나의 중앙 클래스에서 효율적으로 관리하기 위해 사용된다.
 */
@ControllerAdvice
// 필기. ExceptionHandlerController 클래스 처럼 바로 처리하는게 아니라 ExceptionHandler 를 모아둔곳
public class GlobalExceptionHandler {

    /*필기.
        이 곳은 ExceptionHandler 들의 모임하는 장소이다.
     */
    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException exception){

        System.out.println("Global 레벨의 exception 처리 ");   // 필기 콘솔로 exception 처리 메세지 출력

        return "error/nullPointer";  //필기 error 페이지 로 이동 >
    }

    @ExceptionHandler(MemberRegistException.class)
    public String userException(Model model, MemberRegistException exception) {

        System.out.println("Global 레벨의 exception 처리");
        model.addAttribute("exception" , exception);  //필기 MemberRegistException 클래스에 exception 키를 값을 넣어서 보내주기

        return "error/memberRegist"; //필기 페이지 이동
    }

    /* 필기.
        상위 타입인 Exception 을 이용하면 구체적으로 작성하지 않은 타입의 예외가
        발생하더라도 처리가 가능해진다. -> default 처리 용도로 사용할 수 있다.
     */

    @ExceptionHandler(Exception.class) //필기 Exception 클래스를 이용 해서 사용사례
    public String nullPointerExceptionHandler(Exception exception){

        return "error/default";
    }





}
