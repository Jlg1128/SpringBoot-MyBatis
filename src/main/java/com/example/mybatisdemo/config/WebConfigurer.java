package com.example.mybatisdemo.config;

import com.example.mybatisdemo.Responese.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//public class WebConfigurer implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor())
//                .excludePathPatterns("/**");
////                .addPathPatterns("/api/login/**")
////                .excludePathPatterns("/api/person/**");
//    }
//}
