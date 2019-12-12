package com.hsport.wxprogram.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://127.0.0.1:8080/")
            .allowedOrigins("http://localhost:8080/")
            .allowedMethods("HEAD", "GET","PUT", "POST","DELETE", "PATCH","OPTIONS").allowedHeaders("*")
            .allowCredentials(true);
    }
}