package com.ohgiraffers.crud.menu.controller;

import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuAndCategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import com.ohgiraffers.crud.menu.model.service.MenuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private static final Logger logger = LogManager.getLogger(MenuController.class);
    private final MenuService menuService;       //필기 컨트롤러가 최초 시작 될떄 이놈 가져감
    private final MessageSource messageSource;

    @Autowired
    public MenuController(MenuService menuService ,MessageSource messageSource){
        this.menuService  = menuService;
        this.messageSource = messageSource;
    }


    @GetMapping("/list")
    public String findMenuList(Model model){

        List<MenuDTO> menuList = menuService.findAllMenu();  //필기

        model.addAttribute("menuList" , menuList);

        return "menu/list";
    }

    @GetMapping("regist")  //필기 카테고리 종류를 넣어주기 위한 빈가져오기 입니다!
    public void registPage() {}

    @GetMapping(value = "category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CategoryDTO> findCategoryList() {
        return menuService.findAllCategory();
    }
    @PostMapping("regist")
    public String registMenu(MenuDTO newMenu, RedirectAttributes rttr, Locale locale) {

        menuService.registNewMenu(newMenu);
        /* 필기. locale : 지역(나라) 에 대한 정보 다국어 처리와 관련 된 정보 */
        logger.info("Locale : {}" ,locale);
        rttr.addFlashAttribute("successMessage" , messageSource.getMessage("registMenu",new Object[]{newMenu.getName(), newMenu.getPrice()}, locale));

        return "redirect:/menu/list";

    }

    @GetMapping("joinCategory/list")
    public String menuAndCategoryList(Model model){

        List<MenuAndCategoryDTO> menuAndCategoryList = menuService.findAllMenuCategory();

        model.addAttribute("menuAndCategoryList" ,menuAndCategoryList);

        return "menu/joinMenu";
    }

    @GetMapping("delete")
    public void delete() {}

    @PostMapping("/delete")
    public String deleteMenuByCode(@RequestParam("code") int code ,RedirectAttributes rttr, Locale locale) {

        menuService.deleteMenuByCode(code);
        rttr.addFlashAttribute("successMessage" , messageSource.getMessage("deleteMenu" ,null ,locale));

        return "redirect:/menu/list";   //필기 삭제후 표시할 페이지
    }

}
