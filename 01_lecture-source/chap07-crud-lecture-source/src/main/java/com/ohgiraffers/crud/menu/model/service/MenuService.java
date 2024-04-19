package com.ohgiraffers.crud.menu.model.service;

import com.ohgiraffers.crud.menu.model.dao.MenuMapper;
import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service  //필기 빈으로 사용하기위해 사용되는 어노테이션 입니다
public class MenuService {

    private final MenuMapper menuMapper; //필기 mapper 필드에 기본 생성해서 사용하기 위해

    @Autowired          //필기 이놈으로 new 생성안하고 사용하기 위해서 만듬
    public MenuService(MenuMapper menuMapper){
        this.menuMapper = menuMapper;
    }
    public List<MenuDTO> findAllMenu(){

        return menuMapper.findAllMenus();  //필기
    }


    public List<CategoryDTO> findAllCategory() {

        return menuMapper.findAllCategory();
    }

    /* 필기.
        @Transactional 어노테이션은 스프링 프레임워크에서 제공하는 트랙잭션 관리()
        트랜젝션은 데이터베이스의 상태를 변화시키는 일(작업)을 하나의 단위로 묶는 작업을 의미한다.
        데이터 조작에 관련 된 작업이 일어날 떄(c, u , d) 메소드 실행이 완료되면 commit
        예외가 발생하게 되면 rollback
     */
    @Transactional  //필기 다른값이 들어가면 롤백같은 데이터 조작읋 도와주는 어노테이션
    public void registNewMenu(MenuDTO newMenu) {
        menuMapper.registNewMenu(newMenu);
    }
}
