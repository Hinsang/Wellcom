package com.Wellcom.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    /*
    // 스프링 시큐리티에서 CORS 설정을 하지 않았다면 여기서 CORS 설정을 할 수 있다.
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
    */

    /*@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE");
    }*/

    /*@Bean
    public CorsFilters corsFilters() {
        return new CorsFilters();
    }*/

    @Value("${file.path}")
    private String uploadFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:///" + uploadFolder)
                .setCachePeriod(60*10*6) // 캐시 1시간 유지
                .resourceChain(true)
                .addResolver(new PathResourceResolver());

    }

    // 리액트와 스프링부트의 포트가 다른 경우 (CORS 추가)
    /*@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }*/

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        WebMvcConfigurer.super.addViewControllers(registry);

        // 해당하지 않는 URL을 입력시 `/` 경로를 포워딩
        registry.addViewController("/{spring:\\w+}")
                .setViewName("forward:/");

        registry.addViewController("/**/{spring:\\w+}")
                .setViewName("forward:/");

        // \\.js, \\.css, /upload 경로를 제외하고 루트 경로로 포워딩 시킨다.
        // /upload 경로로 이미지가 호출될 때 이미지가 호출되지 않고 루트 경로로 포워딩 되는 문제가 있었으므로 예외 설정을 해주었다. (이곳에서 원인을 찾는데 시간을 많이 소요했다..)
        registry.addViewController("/{spring:\\w+}/**{spring:?!(\\.js|\\.css|\\|/upload)$}")
                .setViewName("forward:/");

        // 로그인 처리 URL 제외 설정
        /*registry.addViewController("/login")
                .setViewName("forward:/login");*/

    }

}
