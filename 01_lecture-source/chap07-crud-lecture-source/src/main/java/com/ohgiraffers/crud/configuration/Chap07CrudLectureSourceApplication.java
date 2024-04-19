package com.ohgiraffers.crud.configuration;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.ohgiraffers.crud" ,annotationClass = Mapper.class)
//필기  위 어노테이션은 마이바틱스에서 제공하는 어노테이션 crud 아래 mapper 찾기
public class Chap07CrudLectureSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chap07CrudLectureSourceApplication.class, args);
	}

}
