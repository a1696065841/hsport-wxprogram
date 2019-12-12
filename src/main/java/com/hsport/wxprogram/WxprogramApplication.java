package com.hsport.wxprogram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
public class WxprogramApplication {
	public static void main(String[] args) {
		SpringApplication.run(WxprogramApplication.class, args);
	}

}
