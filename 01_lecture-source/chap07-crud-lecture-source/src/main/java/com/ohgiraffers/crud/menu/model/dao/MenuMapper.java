package com.ohgiraffers.crud.menu.model.dao;

import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuAndCategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper              //필기 xml 과 연결시키는 mapper
public interface MenuMapper {


    List<MenuDTO> findAllMenus();  //필기  값이 담겨있고. 쿼리문에 작동할 이름 이라고 생각하면 좋다.

    List<CategoryDTO> findAllCategory();

    void registNewMenu(MenuDTO newMenu);

    List<MenuAndCategoryDTO> findAllMenuCategory();

    void deleteMenuByCode(int code);
}
