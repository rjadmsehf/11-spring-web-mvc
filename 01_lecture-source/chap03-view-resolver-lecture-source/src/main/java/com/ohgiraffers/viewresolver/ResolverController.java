package com.ohgiraffers.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/*")
public class ResolverController {

    @GetMapping("string")
    public String stringReturning(Model model){

        model.addAttribute("forwardMessage","문자열로 뷰 이름 반환함..");

        /* 필기.
            문자열로 뷰 이름을 반환 한다는 것은 반환 후
            ThymeleafViewResolver 에게 너가 앞에 resources/template 붙이고
            뒤에는 .html 붙여 라는 의미로 해석할 수 있다.
         */

        return "result";
    }

    @GetMapping("string-redirect")
    public String stringRedirect(){

        /* 필기 접두사로 redirect: 을 하면 forward 방식이 아닌
            redirect 를 시켜준다.
         */

        return "redirect:/";  //필기 주소만 입력해주면 알아서 찾아간다
    }

    @GetMapping("modelandview-redirect")
    public ModelAndView modelAndViewRedirect(ModelAndView mv){

        mv.setViewName("redirect:/");

        return mv;

    }

    @GetMapping("modelandview-redirect-attr")
    public ModelAndView modelAndViewRedirect(ModelAndView mv, RedirectAttributes rttr){

        rttr.addFlashAttribute("flashMessage2","ModelAndView 를 이용한 redirect attr");
        mv.setViewName("redirect:/");

        return mv;
    }
}
