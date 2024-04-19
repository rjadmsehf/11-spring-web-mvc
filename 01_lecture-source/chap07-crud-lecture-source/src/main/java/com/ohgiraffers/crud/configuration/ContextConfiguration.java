package com.ohgiraffers.crud.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

@Configuration
@ComponentScan(basePackages = "com.ohgiraffers.crud")
public class ContextConfiguration {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:/messages/message");
        source.setDefaultEncoding("UTF-8");
        /* 필기 제공하지 않는 언어로 요청시 MessageSource 에서 사용할 default 언어 한국 */
        Locale.setDefault(Locale.KOREA);
        return source;
    }
}


