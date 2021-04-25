package com.shop.springboot.config;

import com.shop.springboot.entity.Product;
import com.shop.springboot.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebServiceConfig implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor loginInterceptor;
//    @Autowired
//    ProductAuthInterceptor productAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns("/post/page")
                .addPathPatterns("/post/**")
                .addPathPatterns("/comment/**");
//
//        registry.addInterceptor(productAuthInterceptor)
//                .excludePathPatterns("/post/page")
//                .addPathPatterns("/post/**")
//                .addPathPatterns("/comment/**");
    }
}